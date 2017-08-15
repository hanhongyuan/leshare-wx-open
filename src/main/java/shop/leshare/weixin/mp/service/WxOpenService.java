package shop.leshare.weixin.mp.service;

import me.chanjar.weixin.common.exception.WxErrorException;
import shop.leshare.weixin.mp.bean.WxOpenVerifyMessage;

import java.io.IOException;

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
	
	/**
	 * 第三方平台component_access_token是第三方平台的下文中接口的调用凭据，也叫做令牌（component_access_token）。每个令牌是存在有效期（2小时）的，且令牌的调用不是无限制的，请第三方平台做好令牌的管理，在令牌快过期时（比如1小时50分）再进行刷新。
	 * 接口调用请求说明
	 * http请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/component/api_component_token
	 * POST数据示例:
	 * {
	 *   "component_appid":"appid_value" ,
	 *   "component_appsecret": "appsecret_value",
	 *   "component_verify_ticket": "ticket_value"
	 * }
	 * @return
	 * @throws WxErrorException
	 * @throws IOException
	 */
	String queryComponentAccessToken();
	
	/**
	 * 该API用于获取预授权码。预授权码用于公众号或小程序授权时的第三方平台方安全验证。
	 * 接口调用请求说明
	 * http请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=xxx
	 * POST数据示例:
	 * {
	 *   "component_appid":"appid_value"
	 * }
	 * @param componentAccessToken
	 * @return
	 */
	String queryPreAuthCode(String componentAccessToken);
}
