package shop.leshare.weixin.open.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.leshare.weixin.open.bean.OpenUserNotify;

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
 *         CreateTime：8/31/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WxOpenUserNotifyMapperTest {
	
	@Autowired
	private WxOpenUserNotifyMapper wxOpenUserNotifyMapper;
	
	@Test
	public void findByAppid() throws Exception {
		
		List<OpenUserNotify> list = wxOpenUserNotifyMapper.findByAppid("test");
		list.forEach(System.out::println);
	}
	
	@Test
	public void addUserNotify() throws Exception {
		
		OpenUserNotify u = new OpenUserNotify();
		u.setAppId("test");
		u.setOpenId("test");
		
		wxOpenUserNotifyMapper.addUserNotify(u);
		
	}
	
}