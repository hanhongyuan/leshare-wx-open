package shop.leshare.weixin.open.handler;

import com.google.common.collect.Lists;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMaterialService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpMaterialServiceImpl;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

	private Logger logger = LogManager.getLogger(MsgHandler.class);
	
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
            //TODO 可以选择将消息保存到本地
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
            e.printStackTrace();
        }

        WxMpXmlOutMessage outMessage = null;
        
        //组装回复消息
	    if(StringUtils.equals(wxMessage.getContent(), "什么鬼")){
        	//回复图文
		    WxMpMaterialNews wxMpMaterialNews = null;
		    try {
			    WxMpMaterialService materialService = new WxMpMaterialServiceImpl(wxMpService);
			    wxMpMaterialNews = materialService.materialNewsInfo("YHTuTdC4Hd52a97BpS0i0GigbgBBunfAkQbUD0paNa8");
			    
		    } catch (WxErrorException e) {
			    e.printStackTrace();
		    }
		
		    List<WxMpXmlOutNewsMessage.Item> itemList = Lists.newArrayList();
		    
		    if(wxMpMaterialNews.getArticles() != null){
			    wxMpMaterialNews.getArticles().forEach(article -> {
				    WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				    item.setTitle(article.getTitle());
				    item.setDescription(article.getDigest());
				    item.setPicUrl(article.getThumbUrl());
				    item.setUrl(article.getUrl());
				    itemList.add(item);
			    });
		    }
		
		    outMessage = WxMpXmlOutMessage.NEWS()
				            .articles(itemList)
				            .fromUser(wxMessage.getToUser())
				            .toUser(wxMessage.getFromUser())
				            .build();
		    
	    }
	
	    outMessage = WxMpXmlOutMessage.TEXT()
			    .content(wxMpService.getWxMpConfigStorage().getAuthorizerAppid() + wxMessage.getContent())
			    .fromUser(wxMessage.getToUser())
			    .toUser(wxMessage.getFromUser())
			    .build();
	    
	    logger.info("返回的消息: {}", outMessage.toXml());
	    
        return outMessage;

    }

}
