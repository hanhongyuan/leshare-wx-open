package shop.leshare.weixin.mp.service;

import shop.leshare.weixin.mp.bean.WxOpenVerifyMessage;

/**
 * <p>Title: shop.leshare.weixin.mp.service</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/13/17
 */
public interface WxOpenService {
	
	/**
	 * 处理微信服务器推送的component_verify_ticket。
	 * 出于安全考虑，在第三方平台创建审核通过后，微信服务器每隔10分钟会向第三方的消息接收地址推送一次component_verify_ticket，用于获取第三方平台接口调用凭据
	 * @param requestBody
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param encType
	 * @param msgSignature
	 */
	void saveVerifyTicket(String requestBody, String signature, String timestamp, String nonce, String encType, String msgSignature);

}
