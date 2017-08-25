package shop.leshare.weixin.open.controller.mp.notify;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 服务号: 福建乐享启创科技
 */
@RestController
@RequestMapping("/open/wx1199ca0caee10fb8/callback")
public class Mp001Controller extends BaseController{
	private final Logger logger = LogManager.getLogger(Mp001Controller.class);
	
	@Autowired
	private WxMpService wxMpService;
	
	@Autowired
	private WxMpMessageRouter router;
	
	private final static String APPID = "wx1199ca0caee10fb8";
	
	@GetMapping(produces = "text/plain;charset=utf-8")
	public String authGet(@RequestParam(name = "signature", required = false) String signature,
	                      @RequestParam(name = "timestamp", required = false) String timestamp,
	                      @RequestParam(name = "nonce", required = false) String nonce,
	                      @RequestParam(name = "echostr", required = false) String echostr) {
		
		logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature,
				timestamp, nonce, echostr);
		
		if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
			throw new IllegalArgumentException("请求参数非法，请核实!");
		}

		if (this.wxMpService.checkSignature(timestamp, nonce, signature)) {
			return echostr;
		}
		
		return "非法请求";
	}
	
	@PostMapping(produces = "application/xml; charset=UTF-8")
	public String post(@RequestBody String requestBody,
	                   @RequestParam("signature") String signature,
	                   @RequestParam("timestamp") String timestamp,
	                   @RequestParam("nonce") String nonce,
	                   @RequestParam(name = "encrypt_type",
			                   required = false) String encType,
	                   @RequestParam(name = "msg_signature",
			                   required = false) String msgSignature) {
		
		return receiveMsg(APPID, requestBody, signature, timestamp, nonce, encType, msgSignature);
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
