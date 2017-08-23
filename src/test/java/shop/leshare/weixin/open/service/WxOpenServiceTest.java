package shop.leshare.weixin.open.service;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
	
	@Autowired
	private UserService userService;
	
	@Test
	public void saveUserInfo() throws Exception {
//		wxOpenService.saveUserInfo("wx36b9de83df162a4f");
		
		String appId = "wx1199ca0caee10fb8";
		
		wxMpService.getWxMpConfigStorage().setAuthorizerAppid(appId);
		
		WxMpUserList userList = wxMpService.getUserService().userList(null);
		
		while(userList != null && userList.getCount() > 0){
			
			
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