package shop.leshare.weixin.open.controller.mp.notify;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Title: shop.leshare.weixin.open.controller.mp.notify</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/23/17
 */
@Component
public abstract class BaseController {
	
	private Logger logger = LogManager.getLogger(BaseController.class);
	
	@Autowired
	private WxMpService wxMpService;
	
	@Autowired
	private WxMpMessageRouter router;
	
	public String receiveMsg(String appId, String requestBody, String signature, String timestamp,
	                       String nonce, String encType, String msgSignature){
		
		this.logger.info(
				"\n接收{}微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				appId, signature, encType, msgSignature, timestamp, nonce, requestBody);
		
		this.wxMpService.getWxMpConfigStorage().setAuthorizerAppid(appId);
		
		if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
			throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
		}
		
		String out = null;
		if (encType == null) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
			WxMpXmlOutMessage outMessage = this.route(inMessage);
			if (outMessage == null) {
				return "";
			}
			
			out = outMessage.toXml();
		} else if ("aes".equals(encType)) {
			// aes加密的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
					requestBody, this.wxMpService.getWxMpConfigStorage(), timestamp,
					nonce, msgSignature);
			this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
			WxMpXmlOutMessage outMessage = this.route(inMessage);
			if (outMessage == null) {
				return "";
			}
			
			out = outMessage.toEncryptedXml(this.wxMpService.getWxMpConfigStorage());
		}
		
		this.logger.debug("\n组装回复信息：{}", out);
		
		return out;
		
	}
	
	private WxMpXmlOutMessage route(WxMpXmlMessage message) {
		try {
			return this.router.route(message);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		
		return null;
	}
}
