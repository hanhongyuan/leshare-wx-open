package shop.leshare.weixin.mp.service.impl;

import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.util.crypto.WxMpCryptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import shop.leshare.weixin.mp.bean.WxOpenVerifyMessage;
import shop.leshare.weixin.mp.manage.RedisStringManage;
import shop.leshare.weixin.mp.service.WxOpenService;

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
		
		WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
		configStorage.setAppId("wxbf9fe3d5bbdf718d");
		configStorage.setSecret("9406ec9d9263d591f9badc706310bff4");
		configStorage.setToken("leshare-open");
		configStorage.setAesKey("SFitQqyOJUJiQ1bWT6rNnNBNy64M0DfQkk7vPN3j816");
		
		try {
			SHA1.gen(configStorage.getToken(), timestamp, nonce).equals(signature);
		} catch (Exception e) {
			this.logger.error("Checking signature failed, and the reason is :" + e.getMessage());
			return ;
		}
		
		WxMpCryptUtil cryptUtil = new WxMpCryptUtil(configStorage);
		String respXml = cryptUtil.decrypt(msgSignature, timestamp, nonce, requestBody);
		
		logger.info("解密后内容: {}", respXml);
		
		WxOpenVerifyMessage verifyMessage = WxOpenVerifyMessage.fromXml(respXml);
		
		String key = "component_verify_ticket";
		redisTemplate.addString(key, verifyMessage.getComponentVerifyTicket());
		
		logger.info("保存微信服务器推送的component_verify_ticket到redis中: {}", verifyMessage);
	}
}
