package shop.leshare.weixin.open.service;

import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Title: shop.leshare.weixin.mp.service</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/18/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WxOpenServiceTest {
	
	@Autowired
	private WxMpService wxMpService;
	
	@Test
	public void saveUserInfo() throws Exception {
		wxOpenService.saveUserInfo("wx36b9de83df162a4f");
	}
	
	@Autowired
	private WxOpenService wxOpenService;
	
	@Test
	public void saveVerifyTicket() throws Exception {
	}
	
	@Test
	public void queryComponentAccessToken() throws Exception {
	}
	
	@Test
	public void queryPreAuthCode() throws Exception {
	}
	
	@Test
	public void saveAuthCode() throws Exception {
	}
	
	@Test
	public void authorizer() throws Exception {
	}
	
	@Test
	public void checkAllAccessToken() throws Exception {
		
		wxOpenService.checkAllAccessToken();
	}
	
	@Test
	public void refreshAccessToken() throws Exception {
	
	}
	
}