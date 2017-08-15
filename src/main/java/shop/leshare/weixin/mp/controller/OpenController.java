package shop.leshare.weixin.mp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.leshare.weixin.mp.service.WxOpenService;

/**
 * <p>Title: shop.leshare.weixin.mp.controller</p>
 * <p/>
 * <p>
 *  微信开放平台接口
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/8/17
 */
@RestController
@RequestMapping("/open")
public class OpenController {
	
	private Logger logger = LogManager.getLogger(OpenController.class);
	
	@Autowired
	private WxOpenService wxOpenService;
	
	@PostMapping("/notice")
	public String componentVerifyTicket(@RequestBody String requestBody,
	                                    @RequestParam("signature") String signature,
	                                    @RequestParam("timestamp") String timestamp,
	                                    @RequestParam("nonce") String nonce,
	                                    @RequestParam(name = "encrypt_type",
			                                    required = false) String encType,
	                                    @RequestParam(name = "msg_signature",
			                                    required = false) String msgSignature){
		
		logger.info("\n开放平台接收微信component_verify_ticket：[signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				signature, encType, msgSignature, timestamp, nonce, requestBody);
		
		wxOpenService.saveVerifyTicket(requestBody, signature, timestamp, nonce, encType, msgSignature);
		
//		WxMpServiceApacheHttpClientImpl wxMpServiceApacheHttpClient = new WxMpServiceApacheHttpClientImpl();
//		wxMpServiceApacheHttpClient.setWxMpConfigStorage(configStorage);
//
//		WxOpenAccessTokenQuery accessTokenQuery = new WxOpenAccessTokenQuery();
//		accessTokenQuery.setComponent_appid(configStorage.getAppId());
//		accessTokenQuery.setComponent_appsecret(configStorage.getSecret());
//		accessTokenQuery.setComponent_verify_ticket(verifyMessage.getComponentVerifyTicket());
//
//		String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
//		try {
//			String result = SimplePostRequestExecutor.create(wxMpServiceApacheHttpClient).execute(url, accessTokenQuery.toJson());
//			if(!StringUtils.isEmpty(result)){
//				WxOpenAccessTokenResult accessTokenResult = WxOpenAccessTokenResult.fromJson(result);
//
//				logger.info("获取第三方平台component_access_token: {}", accessTokenResult);
//
//				String preAuthCodeUrl = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=" + accessTokenResult.getComponent_access_token();
//				WxOpenPreAuthCodeQuery appidQuery = new WxOpenPreAuthCodeQuery();
//				appidQuery.setComponent_appid(configStorage.getAppId());
//				logger.info("请求pre_auth_code, 请求报文{}", appidQuery.toJson());
//				String preAuthCodeJson = SimplePostRequestExecutor.create(wxMpServiceApacheHttpClient).execute(preAuthCodeUrl, appidQuery.toJson());
//				WxOpenPreAuthCodeResult preAuthCodeResult = WxOpenPreAuthCodeResult.fromJson(preAuthCodeJson);
//				logger.info(preAuthCodeResult);
//			}
//		} catch (WxErrorException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		return "success";
	}
	
	
	
}
