package shop.leshare.weixin.open.mapper;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import shop.leshare.weixin.open.bean.MpUser;

import java.util.List;

/**
 * <p>Title: shop.leshare.weixin.open.mapper</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/22/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WxMpUserMapperTest {
	
	
	@Autowired
	private WxMpUserMapper wxMpUserMapper;
	
	@Test
	public void findUser() throws Exception {
	
		MpUser mpUser = wxMpUserMapper.findUser("11111");
		System.out.println(mpUser);
	}
	
	@Test
	public void addUser() throws Exception {
		
		wxMpUserMapper.addUser(MpUser.fromWxUser(getWxMpUser(), "12345"));
	}
	
	@Test
	public void subscribeUser() throws Exception {
		wxMpUserMapper.unsubscribeUser("11111");
	}
	
	@Test
	public void updateUser() throws Exception {
		
		wxMpUserMapper.updateUser(MpUser.fromWxUser(getWxMpUser(), "12345"));
	}
	
	private WxMpUser getWxMpUser(){
		WxMpUser user = new WxMpUser();
		user.setOpenId("11111");
		user.setNickname("lynn");
		user.setSex("nv");
		user.setLanguage("CN");
		user.setCity("FUZHOU");
		user.setProvince("FUJIAN");
		user.setCountry("CHINA");
		user.setHeadImgUrl("hahaha");
		user.setSubscribeTime(1382694957L);
		user.setUnionId("12345");
		user.setRemark("hahahah");
		user.setTagIds(new Long[]{1L, 2L});
		user.setGroupId(0);
		
		return user;
	}
	
}