package shop.leshare.weixin.mp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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