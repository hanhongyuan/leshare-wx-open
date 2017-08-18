package shop.leshare.weixin.mp.schedule;

import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import shop.leshare.weixin.mp.service.WxOpenService;

/**
 * <p>Title: shop.leshare.weixin.mp.schedule</p>
 * <p/>
 * <p>
 * Description: 授权码过期检查
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/15/17
 */
@Component
public class AuthExpiredCheck {
	
	private Logger logger = LogManager.getLogger(AuthExpiredCheck.class);
	
	@Autowired
	private WxOpenService wxOpenService;
	
	@Scheduled(cron = "0 0/10 * * * ?")
	public void check(){
		
		try {
			logger.info("授权码过期检查");
			wxOpenService.checkAllAccessToken();
		} catch (WxErrorException e) {
			logger.error(e.toString(), e);
		}
		
	}
	
}
