package shop.leshare.weixin.mp.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.leshare.weixin.mp.bean.WxOpenUser;

import java.util.List;

import static org.junit.Assert.*;

/**
 * <p>Title: shop.leshare.weixin.mp.mapper</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/18/17
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxOpenUserMapperTest {
	
	@Autowired
	private WxOpenUserMapper wxOpenUserMapper;
	
	@Test
	public void findUserList() throws Exception {
		
		List<WxOpenUser> users = wxOpenUserMapper.findUserList();
		for (WxOpenUser user : users) {
			System.out.println(user);
		}
	
	}
	
	@Test
	public void addUser() throws Exception {
		
		wxOpenUserMapper.addUser(createNewUser());
		
	}
	
	private WxOpenUser createNewUser(){
		WxOpenUser user = new WxOpenUser();
		user.setApp_id("app_id");
		user.setNick_name("nick_name");
		user.setHead_img("head_img");
		user.setService_type_info(1);
		user.setVerify_type_info(1);
		user.setUser_name("test");
		user.setSignature("signature");
		user.setPrincipal_name("principal_name");
		user.setAlias("alias");
		user.setBusiness_info("business_info");
		user.setQrcode_url("qrcode_url");
		user.setAuthorization_info("authorization_info");
		user.setFunc_info("test");
		user.setType(1);
		user.setIs_use(1);
		
		return user;
	}
}