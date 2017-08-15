package shop.leshare.weixin.mp.service.impl;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.util.crypto.WxMpCryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.leshare.weixin.mp.bean.*;
import shop.leshare.weixin.mp.manage.RedisStringManage;
import shop.leshare.weixin.mp.service.WxOpenService;

import java.io.IOException;

/**
 * <p>Title: shop.leshare.weixin.mp.service.impl</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/13/17
 */
@Service
public class WxOpenServiceImpl implements WxOpenService{
	
	private Logger logger = LogManager.getLogger(WxOpenServiceImpl.class);
	
	@Autowired
	private RedisStringManage redisTemplate;
	
	@Autowired
	private WxMpConfigStorage configStorage;
	
	@Autowired
	private WxMpService wxMpService;
	
	private static final String API_COMPONENT_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
	private static final String PRE_AUTH_CODE_URL = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=%s";
	private static final String COMPONENT_VERIFY_TICKET_KEY = "COMPONENT_VERIFY_TICKET";
	private static final String COMPONENT_ACCESS_TOKEN_KEY = "COMPONENT_ACCESS_TOKEN";
	private static final String PRE_AUTH_CODE_KEY = "PRE_AUTH_CODE";
	
	/**
	 * 处理微信服务器推送的component_verify_ticket, 保存数据到redis中
	 * 出于安全考虑，在第三方平台创建审核通过后，微信服务器每隔10分钟会向第三方的消息接收地址推送一次component_verify_ticket，用于获取第三方平台接口调用凭据
	 *
	 * @param requestBody
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param encType
	 * @param msgSignature
	 */
	@Override
	public void saveVerifyTicket(String requestBody, String signature, String timestamp, String nonce, String encType, String msgSignature) {
		
		try {
			SHA1.gen(configStorage.getToken(), timestamp, nonce).equals(signature);
		} catch (Exception e) {
			this.logger.error("Checking signature failed, and the reason is :" + e.getMessage());
			return ;
		}
		
		WxMpCryptUtil cryptUtil = new WxMpCryptUtil(configStorage);
		String respXml = cryptUtil.decrypt(msgSignature, timestamp, nonce, requestBody);
		
		logger.info("解密后内容: {}", respXml);
		
		WxOpenVerifyMessage verifyMessage = null;
		
		try {
			verifyMessage = WxOpenVerifyMessage.fromXml(respXml);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		if(verifyMessage == null || StringUtils.isEmpty(verifyMessage.getComponentVerifyTicket())){
			logger.error("component_verify_ticket解析失败, requestBody={}", requestBody);
			return ;
		}
		
		redisTemplate.addString(COMPONENT_VERIFY_TICKET_KEY, verifyMessage.getComponentVerifyTicket());
		
		logger.info("保存微信服务器推送的component_verify_ticket到redis中: {}", verifyMessage);
	}
	
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
	@Override
	public String queryComponentAccessToken() {
		
		//get component_access_token from redis
		Object componentAccessTokenObj = redisTemplate.getString(COMPONENT_ACCESS_TOKEN_KEY);
		
		if(componentAccessTokenObj != null){
			return componentAccessTokenObj.toString();
		}
		
		Object componentVerifyTicket = redisTemplate.getString(COMPONENT_VERIFY_TICKET_KEY);
		
		if(componentVerifyTicket == null) return "";
		
		WxOpenAccessTokenQuery accessTokenQuery = new WxOpenAccessTokenQuery();
		accessTokenQuery.setComponent_appid(configStorage.getAppId());
		accessTokenQuery.setComponent_appsecret(configStorage.getSecret());
		accessTokenQuery.setComponent_verify_ticket(componentVerifyTicket.toString());
		
		try {
			String jsonResp = SimplePostRequestExecutor.create(wxMpService.getRequestHttp()).execute(API_COMPONENT_TOKEN_URL, accessTokenQuery.toJson());
			
			WxOpenAccessTokenResult accessTokenResult = WxOpenAccessTokenResult.fromJson(jsonResp);
			logger.info("获取第三方平台component_access_token: {}", accessTokenResult);
			
			//save component access token to redis with 6000s
			if(!StringUtils.isEmpty(accessTokenResult.getComponent_access_token())){
				redisTemplate.addString(COMPONENT_ACCESS_TOKEN_KEY, 6000L, accessTokenResult.getComponent_access_token());
				logger.info("save component access token to redis with 6000s, componentAccessToken={}", accessTokenResult.getComponent_access_token());
			}
			
			return accessTokenResult.getComponent_access_token();
			
		} catch (WxErrorException e) {
			logger.error(e.toString(), e);
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
		
		return "";
	}
	
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
	@Override
	public String queryPreAuthCode(String componentAccessToken){
		
		WxOpenPreAuthCodeQuery appidQuery = new WxOpenPreAuthCodeQuery();
		appidQuery.setComponent_appid(configStorage.getAppId());
		logger.info("请求pre_auth_code, 请求报文: {}", appidQuery.toJson());
		String preAuthCodeJson = null;
		try {
			preAuthCodeJson = SimplePostRequestExecutor.create(wxMpService.getRequestHttp()).execute(String.format(PRE_AUTH_CODE_URL, componentAccessToken), appidQuery.toJson());
		} catch (WxErrorException e) {
			logger.error(e.toString(), e);
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
		
		if(StringUtils.isEmpty(preAuthCodeJson)){
			logger.error("请求pre_auth_code失败.");
			return "";
		}
		
		WxOpenPreAuthCodeResult preAuthCodeResult = WxOpenPreAuthCodeResult.fromJson(preAuthCodeJson);
		logger.info("请求pre_auth_code，返回内容: " + preAuthCodeResult);
		
		if(StringUtils.isEmpty(preAuthCodeResult.getPre_auth_code())){
			logger.error("请求pre_auth_code失败.");
			return "";
		}
		
		//save preAuthCode to redis
		redisTemplate.addString(PRE_AUTH_CODE_KEY, preAuthCodeResult.getExpires_in(), preAuthCodeResult.getPre_auth_code());
		
		return preAuthCodeResult.getPre_auth_code();
	}
}
