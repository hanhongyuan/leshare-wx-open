package shop.leshare.weixin.mp.controller;

import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.leshare.common.entity.LeResponse;
import shop.leshare.common.entity.Result;
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
			                                    required = false) String msgSignature) throws WxErrorException {
		
		logger.info("\n开放平台接收微信component_verify_ticket：[signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				signature, encType, msgSignature, timestamp, nonce, requestBody);
		
		wxOpenService.saveVerifyTicket(requestBody, signature, timestamp, nonce, encType, msgSignature);
		
		String componentAccessToken = wxOpenService.queryComponentAccessToken();
		
		if(!StringUtils.isEmpty(componentAccessToken)){
			wxOpenService.queryPreAuthCode(componentAccessToken);
		}
		
		return "success";
	}
	
	@GetMapping("/auth_code")
	public LeResponse<Result> openAuthCode(@RequestParam("auth_code") String authCode,
	                                       @RequestParam("expires_in") long expiresIn) throws WxErrorException {
		
		logger.info("开放平台接收用户授权码:{}, 过期时间:{}", authCode, expiresIn);
		
		Result result = wxOpenService.saveAuthCode(authCode, expiresIn);
		if(result.check()){
			result = wxOpenService.authorizer();
		}
		
		return LeResponse.create(0, result);
	}
	
}
