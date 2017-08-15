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
	private static final String API_CREATE_PREAUTHCODE_URL = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=%s";
	private static final String API_QUERY_AUTH_URL = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=%s";
	
	private static final String COMPONENT_VERIFY_TICKET_KEY = "COMPONENT_VERIFY_TICKET";
	private static final String COMPONENT_ACCESS_TOKEN_KEY = "COMPONENT_ACCESS_TOKEN";
	private static final String PRE_AUTH_CODE_KEY = "PRE_AUTH_CODE";
	private static final String AUTH_CODE_KEY = "AUTH_CODE";
	
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
			logger.info("query component_access_token from redis: {} ", componentAccessTokenObj.toString());
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
			preAuthCodeJson = SimplePostRequestExecutor.create(wxMpService.getRequestHttp()).execute(String.format(API_CREATE_PREAUTHCODE_URL, componentAccessToken), appidQuery.toJson());
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
	
	/**
	 * 保存auth_code
	 * @param authCode
	 * @param expiresIn
	 */
	@Override
	public Result saveAuthCode(String authCode, long expiresIn) {
	
		if(StringUtils.isEmpty(authCode)) {
			logger.error("授权码不能为空.");
			return Result.fail();
		}
		
		//save to redis
		redisTemplate.addString(AUTH_CODE_KEY, expiresIn, authCode);
		
		return Result.success();
	}
	
	/**
	 * 该API用于使用授权码换取授权公众号或小程序的授权信息，并换取authorizer_access_token和authorizer_refresh_token。
	 * 授权码的获取，需要在用户在第三方平台授权页中完成授权流程后，在回调URI中通过URL参数提供给第三方平台方。
	 * 请注意，由于现在公众号或小程序可以自定义选择部分权限授权给第三方平台，因此第三方平台开发者需要通过该接口来获取公众号或小程序具体授权了哪些权限，
	 * 而不是简单地认为自己声明的权限就是公众号或小程序授权的权限。
	 * <p>
	 * 接口调用请求说明
	 * http请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=xxxx
	 * POST数据示例:
	 * {
	 * "component_appid":"appid_value" ,
	 * "authorization_code": "auth_code_value"
	 * }
	 *
	 * @return
	 */
	@Override
	public Result authorizer() {
		
		Object authCodeObj = redisTemplate.getString(AUTH_CODE_KEY);
		if(authCodeObj == null){
			return Result.fail();
		}
		
		WxOpenAuthCodeQuery authCodeQuery = new WxOpenAuthCodeQuery();
		authCodeQuery.setComponent_appid(configStorage.getAppId());
		authCodeQuery.setAuthorization_code(authCodeObj.toString());
		
		String json = "";
		
		try {
			json = SimplePostRequestExecutor.create(wxMpService.getRequestHttp()).execute(String.format(API_QUERY_AUTH_URL, queryComponentAccessToken()), authCodeQuery.toJson());
		} catch (WxErrorException e) {
			logger.error(e.toString(), e);
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
		
		if(StringUtils.isEmpty(json)){
			logger.error("使用授权码换取授权公众号或小程序的授权信息失败.");
			return Result.fail();
		}
		
		WxOpenAuthCodeResult authCodeResult = WxOpenAuthCodeResult.fromJson(json);
		logger.info("使用授权码换取授权公众号或小程序的授权信息，URL:{}, \nparams:{}, \nrespJson:{}, \nObject: {} ", API_QUERY_AUTH_URL, authCodeQuery.toJson(), json, authCodeResult);
		
		//save to redis
		
		
		return Result.success();
	}
	
}
