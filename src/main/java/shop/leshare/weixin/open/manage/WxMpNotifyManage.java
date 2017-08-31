package shop.leshare.weixin.open.manage;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceApacheHttpClientImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import shop.leshare.common.entity.Result;
import shop.leshare.weixin.open.bean.OpenUserNotify;

import java.util.List;

/**
 * <p>Title: shop.leshare.manage</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/2/17
 */
@Service
public class WxMpNotifyManage {

	private Logger logger = LogManager.getLogger(WxMpNotifyManage.class);
	
	//客户留言通知
	private final String NEW_MSG_TEMPLATE_ID = "MnuLrGWF1Ps9fHmFQsXEakaNV1y98k1RR9znW7Qu01k";
	
	//通知字体颜色
	private final String fontColor = "#576B95";
	
	private WxMpService getCompanyService(){
		
		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId("wx1199ca0caee10fb8");
		config.setSecret("ce5841880568e743f2be5dd0ba601884");
		config.setToken("n1q4tO0kf9aEIXn6lYEQtnVESFKkXATF");
		config.setAesKey("xXvN9wOE5LxTS3ihB55XDy4pa7zHL2Ct6AwlSDJkz67");
		
		WxMpServiceApacheHttpClientImpl mpService = new WxMpServiceApacheHttpClientImpl();
		mpService.setWxMpConfigStorage(config);
		
		return mpService;
	}
	
	/**
	 * 客户公众号新留言消息模板消息
	 * @return
	 */
	public Result sendLeMsgNotify(List<OpenUserNotify> openUserNotifies, String mpName, String username, String content, String msgTime){
		
		for (OpenUserNotify openUserNotify : openUserNotifies) {
			
			if(StringUtils.isEmpty(openUserNotify.getOpenId())) continue;
			
			WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
					.toUser(openUserNotify.getOpenId())
					.templateId(NEW_MSG_TEMPLATE_ID)
					.build();
			
			templateMessage.addWxMpTemplateData(new WxMpTemplateData("first", "您的公众号[" + mpName + "], 有新的留言消息.", "#000000"));
			
			templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword1", username, fontColor));
			templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword2", content, fontColor));
			
			templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword3", msgTime, fontColor));
			
			try {
				String msgId = getCompanyService().getTemplateMsgService().sendTemplateMsg(templateMessage);
				logger.info("客户公众号新留言消息通知, 推送给:{}, 推送成功, msgId:{}", openUserNotify.getOpenId(), msgId);
			} catch (WxErrorException e) {
				logger.error(e.toString(), e);
				return Result.fail();
			}
		}
		
		return Result.success();
	}
}
