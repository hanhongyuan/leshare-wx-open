package shop.leshare.weixin.open.controller;

import com.google.common.collect.ImmutableMap;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.leshare.common.entity.LeResponse;
import shop.leshare.common.entity.Result;
import shop.leshare.weixin.open.bean.wx.WxOpenAuthMessage;
import shop.leshare.weixin.open.bean.wx.WxOpenNotice;
import shop.leshare.weixin.open.bean.wx.WxOpenVerifyMessage;
import shop.leshare.weixin.open.service.UserService;
import shop.leshare.weixin.open.service.WxOpenService;

import java.util.List;
import java.util.Map;

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
	
	@Autowired
	private WxMpService wxMpService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/notice")
	public String componentVerifyTicket(@RequestBody String requestBody,
	                                    @RequestParam("signature") String signature,
	                                    @RequestParam("timestamp") String timestamp,
	                                    @RequestParam("nonce") String nonce,
	                                    @RequestParam(name = "encrypt_type",
			                                    required = false) String encType,
	                                    @RequestParam(name = "msg_signature",
			                                    required = false) String msgSignature) throws WxErrorException {
		
		logger.info("\n开放平台接收微信component_verify_ticket：[signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				signature, encType, msgSignature, timestamp, nonce, requestBody);
		
		String respXml = wxOpenService.decryptNotice(requestBody, signature, timestamp, nonce, encType, msgSignature);
		
		logger.info("解析微信通知消息, {}", respXml);
		
		WxOpenNotice notice = WxOpenNotice.fromXml(respXml);
		
		if(StringUtils.equalsIgnoreCase(notice.getTypeInfo(), "component_verify_ticket")){
			wxOpenService.saveVerifyTicket(WxOpenVerifyMessage.fromXml(respXml));
		}else if(StringUtils.equalsIgnoreCase(notice.getTypeInfo(), "unauthorized")){//取消授权通知
			WxOpenAuthMessage authMessage = WxOpenAuthMessage.fromXml(respXml);
			logger.info("取消授权通知, data:{}", authMessage);
			wxOpenService.unauthorized(authMessage.getAuthorizerAppid());
			
		}else if(StringUtils.equalsIgnoreCase(notice.getTypeInfo(), "authorized")){//授权成功通知
			
			WxOpenAuthMessage authMessage = WxOpenAuthMessage.fromXml(respXml);
			logger.info("授权成功通知, data:{}", authMessage);
			long expiresIn = (Long.parseLong(authMessage.getAuthorizationCodeExpiredTime()) * 1000 - System.currentTimeMillis())/1000;
			if(expiresIn > 0){
				String appId = wxOpenService.authorizer(authMessage.getAuthorizationCode());
				syncUserInfo(appId);
			}
			
			
		}else if(StringUtils.equalsIgnoreCase(notice.getTypeInfo(), "updateauthorized")){//授权更新通知
			WxOpenAuthMessage authMessage = WxOpenAuthMessage.fromXml(respXml);
			logger.info("授权更新通知, data:{}", authMessage);
			long expiresIn = (Long.parseLong(authMessage.getAuthorizationCodeExpiredTime()) * 1000 - System.currentTimeMillis())/1000;
			if(expiresIn > 0){
				String appId = wxOpenService.authorizer(authMessage.getAuthorizationCode());
			}
		}
		
		//检测Access_Token是否过期
		logger.info("授权码过期检查");
		wxOpenService.checkAllAccessToken();
		
		return "success";
	}
	
	/**
	 * 从微信服务器全量同步用户数据
	 * @param appId
	 * @throws WxErrorException
	 */
	private void syncUserInfo(String appId) throws WxErrorException {
		
		logger.info("全量同步公众号用户数据， appId:{}", appId);
		wxMpService.getWxMpConfigStorage().setAuthorizerAppid(appId);
		
		WxMpUserList userList = wxMpService.getUserService().userList(null);
		
		while(userList != null && userList.getCount() > 0){
			
			logger.info("全量同步公众号用户数据, 总用户数:{}, 本次拉取数:{}", userList.getTotal(), userList.getCount());
			
			List<String> openIds = userList.getOpenids();
			List<WxMpUser> wxMpUsers = wxMpService.getUserService().userInfoList(openIds);
			
			wxMpUsers.forEach(wxMpUser -> userService.addUser(wxMpUser, appId));
			
			if(!StringUtils.isEmpty(userList.getNextOpenid())){
				userList = wxMpService.getUserService().userList(userList.getNextOpenid());
			}else {
				userList = null;
			}
		}
	}
	
	@GetMapping("/pre_auth_code")
	public LeResponse<Map<String, String>> queryPreAuthCode() throws WxErrorException {
		String preAuthCode = wxOpenService.queryPreAuthCode();
		
		return LeResponse.create(0, ImmutableMap.of("pre_auth_code", preAuthCode));
	}
	
	/**
	 * 请求授权
	 * @param authCode
	 * @param expiresIn
	 * @return
	 * @throws WxErrorException
	 */
	@GetMapping("/auth_code")
	public LeResponse<Result> openAuthCode(@RequestParam("auth_code") String authCode,
	                                       @RequestParam("expires_in") long expiresIn) throws WxErrorException {
		
		logger.info("开放平台接收用户授权码:{}, 过期时间:{}", authCode, expiresIn);
		
		Result result = wxOpenService.saveAuthCode(authCode, expiresIn);
		if(result.check()){
			wxOpenService.authorizer(authCode);
		}
		
		return LeResponse.create(0, result);
	}
	
}
