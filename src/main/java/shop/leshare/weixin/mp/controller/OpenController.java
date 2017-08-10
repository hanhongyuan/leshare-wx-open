package shop.leshare.weixin.mp.controller;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceApacheHttpClientImpl;
import me.chanjar.weixin.mp.util.crypto.WxMpCryptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import shop.leshare.weixin.mp.bean.*;

import java.io.IOException;

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
	private WxMpService wxMpService;
	
	@PostMapping("/notice")
	public String componentVerifyTicket(@RequestBody String requestBody,
	                                    @RequestParam("signature") String signature,
	                                    @RequestParam("timestamp") String timestamp,
	                                    @RequestParam("nonce") String nonce,
	                                    @RequestParam(name = "encrypt_type",
			                                    required = false) String encType,
	                                    @RequestParam(name = "msg_signature",
			                                    required = false) String msgSignature){
		
		logger.info(
				"\n开放平台接收微信component_verify_ticket：[signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				signature, encType, msgSignature, timestamp, nonce, requestBody);
		
		WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
		configStorage.setAppId("wxbf9fe3d5bbdf718d");
		configStorage.setSecret("9406ec9d9263d591f9badc706310bff4");
		configStorage.setToken("leshare-open");
		configStorage.setAesKey("SFitQqyOJUJiQ1bWT6rNnNBNy64M0DfQkk7vPN3j816");
		
		try {
			SHA1.gen(configStorage.getToken(), timestamp, nonce).equals(signature);
		} catch (Exception e) {
			this.logger.error("Checking signature failed, and the reason is :" + e.getMessage());
			return "success";
		}

		WxMpCryptUtil cryptUtil = new WxMpCryptUtil(configStorage);
		String respXml = cryptUtil.decrypt(msgSignature, timestamp, nonce,
				requestBody);

		logger.info("解密后内容: {}", respXml);
		
		WxOpenVerifyMessage verifyMessage = WxOpenVerifyMessage.fromXml(respXml);
		
		WxMpServiceApacheHttpClientImpl wxMpServiceApacheHttpClient = new WxMpServiceApacheHttpClientImpl();
		wxMpServiceApacheHttpClient.setWxMpConfigStorage(configStorage);
		
		WxOpenAccessTokenQuery accessTokenQuery = new WxOpenAccessTokenQuery();
		accessTokenQuery.setComponent_appid(configStorage.getAppId());
		accessTokenQuery.setComponent_appsecret(configStorage.getSecret());
		accessTokenQuery.setComponent_verify_ticket(verifyMessage.getComponentVerifyTicket());
		
		String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
		try {
			String result = SimplePostRequestExecutor.create(wxMpServiceApacheHttpClient).execute(url, accessTokenQuery.toJson());
			if(!StringUtils.isEmpty(result)){
				WxOpenAccessTokenResult accessTokenResult = WxOpenAccessTokenResult.fromJson(result);
				
				String preAuthCodeUrl = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=" + accessTokenResult.getComponent_access_token();
				WxOpenPreAuthCodeQuery appidQuery = new WxOpenPreAuthCodeQuery();
				appidQuery.setComponent_appid(configStorage.getAppId());
				logger.info("请求pre_auth_code, 请求报文{}", appidQuery.toJson());
				String preAuthCodeJson = SimplePostRequestExecutor.create(wxMpServiceApacheHttpClient).execute(preAuthCodeUrl, appidQuery.toJson());
				WxOpenPreAuthCodeResult preAuthCodeResult = WxOpenPreAuthCodeResult.fromJson(preAuthCodeJson);
				logger.info(preAuthCodeResult);
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	
	
}
