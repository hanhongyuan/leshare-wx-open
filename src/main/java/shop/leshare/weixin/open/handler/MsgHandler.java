package shop.leshare.weixin.open.handler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.leshare.common.utils.EmptyCheckUtils;
import shop.leshare.weixin.open.bean.MpUser;
import shop.leshare.weixin.open.bean.OpenUser;
import shop.leshare.weixin.open.bean.OpenUserNotify;
import shop.leshare.weixin.open.manage.WxMpNotifyManage;
import shop.leshare.weixin.open.service.MsgService;
import shop.leshare.weixin.open.service.ShopService;
import shop.leshare.weixin.open.service.UserService;
import shop.leshare.weixin.open.service.WxOpenService;

import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

	private Logger logger = LogManager.getLogger(MsgHandler.class);
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private MsgService msgService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WxOpenService wxOpenService;
	
	@Autowired
	private WxMpNotifyManage wxMpNotifyManage;
	
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context,
                                    WxMpService wxMpService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
            //将消息保存到本地
	        msgService.addMsg(wxMessage);
	
	        if(wxMessage.getMsgType().equalsIgnoreCase("text") ||
			        wxMessage.getMsgType().equalsIgnoreCase("image" )){
		        OpenUser openUser = wxOpenService.findOpenUserByUsername(wxMessage.getToUser());
		        MpUser mpUser = userService.findUserByOpenId(wxMessage.getFromUser());
		
		        logger.debug("准备转发消息给公众号, openUser:{}, mpUser:{}", openUser, mpUser);
		        
		        if(openUser != null && mpUser != null){
			        //公众号消息通知
			        List<OpenUserNotify> notifyList = userService.findNotifyUsers(openUser.getApp_id());
			
			        if(!EmptyCheckUtils.isEmpty(notifyList)){
				
				        wxMpNotifyManage.sendLeMsgNotify(notifyList, openUser.getNick_name(), mpUser.getNick_name(),
						        wxMessage.getContent(), new DateTime(wxMessage.getCreateTime() * 1000).toString("yyyy-MM-dd HH:mm:ss"), wxMessage.getMsgType());
				        
				        logger.info("发送消息给公众号:({})管理员.", openUser.getNick_name());
			        }
		        }
	        }
        }
        
	    WxMpXmlOutMessage outMessage = null;
	
	    //处理公司服务号的订单绑定通知服务
	    if(StringUtils.equals(wxMessage.getToUser(), "gh_14a319bcec72")//仅针对公司服务号
			    && StringUtils.startsWithIgnoreCase(wxMessage.getContent(), "bind")){
		
		    String code = StringUtils.substringAfter(wxMessage.getContent(), "bind");
		    String text = shopService.bindShopNotifyUser(code, wxMessage.getFromUser());
		
		    outMessage = WxMpXmlOutMessage.TEXT().content(text)
				    .fromUser(wxMessage.getToUser())
				    .toUser(wxMessage.getFromUser())
				    .build();
		
		    return outMessage;
	    }
        
        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                    && wxMpService.getKefuService().kfOnlineList()
                    .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                        .fromUser(wxMessage.getToUser())
                        .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            logger.error(e.toString(), e);
        }

        
        
//        //组装回复消息
//	    if(StringUtils.equals(wxMessage.getContent(), "什么鬼")){
//        	//回复图文
//		    WxMpMaterialNews wxMpMaterialNews = null;
//		    try {
//			    WxMpMaterialService materialService = new WxMpMaterialServiceImpl(wxMpService);
//			    wxMpMaterialNews = materialService.materialNewsInfo("YHTuTdC4Hd52a97BpS0i0GigbgBBunfAkQbUD0paNa8");
//
//		    } catch (WxErrorException e) {
//			    e.printStackTrace();
//		    }
//
//		    List<WxMpXmlOutNewsMessage.Item> itemList = Lists.newArrayList();
//
//		    if(wxMpMaterialNews.getArticles() != null){
//			    wxMpMaterialNews.getArticles().forEach(article -> {
//				    WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
//				    item.setTitle(article.getTitle());
//				    item.setDescription(article.getDigest());
//				    item.setPicUrl(article.getThumbUrl());
//				    item.setUrl(article.getUrl());
//				    itemList.add(item);
//			    });
//		    }
//
//		    outMessage = WxMpXmlOutMessage.NEWS()
//				            .articles(itemList)
//				            .fromUser(wxMessage.getToUser())
//				            .toUser(wxMessage.getFromUser())
//				            .build();
//
//	    }
	
//	    outMessage = WxMpXmlOutMessage.TEXT()
//			    .content(wxMpService.getWxMpConfigStorage().getAuthorizerAppid() + wxMessage.getContent())
//			    .fromUser(wxMessage.getToUser())
//			    .toUser(wxMessage.getFromUser())
//			    .build();
	    
	    logger.info("返回的消息: {}", outMessage == null ? "" : outMessage.toXml());
	    
        return outMessage;

    }

}
