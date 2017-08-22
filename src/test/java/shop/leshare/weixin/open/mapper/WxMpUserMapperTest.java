package shop.leshare.weixin.open.mapper;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	public void addUser() throws Exception {
		
		WxMpUser user = new WxMpUser();
		user.setOpenId("11111");
		user.setNickname("name");
		user.setSex("nv");
		user.setLanguage("CN");
		user.setCity("FUZHOU");
		user.setProvince("FUJIAN");
		user.setCountry("CHINA");
		user.setHeadImgUrl("hahaha");
		user.setSubscribeTime(1382694957L);
		user.setUnionId("12345");
		user.setRemark("hahahah");
		user.setTagIds(null);
		user.setGroupId(0);
		
		DateTime dt = new DateTime(user.getSubscribeTime() * 1000);
		
		wxMpUserMapper.addUser(user, "12345", dt.toString("yyyy-MM-dd HH:mm:ss"), StringUtils.join(user.getTagIds(), ","));
	}
	
	@Test
	public void subscribeUser() throws Exception {
		wxMpUserMapper.unsubscribeUser("11111");
	}
	
}