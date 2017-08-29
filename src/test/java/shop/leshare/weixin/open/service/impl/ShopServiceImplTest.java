package shop.leshare.weixin.open.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * <p>Title: shop.leshare.weixin.open.service.impl</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/29/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShopServiceImplTest {
	
	@Autowired
	private ShopServiceImpl shopService;
	
	@Test
	public void bindShopNotifyUser() throws Exception {
		
		String content = "bindxemTgbKrhqiYKMFowcBCQ1Zr7G13bWUT";
		
		if(StringUtils.startsWithIgnoreCase(content, "bind")) {
			
			String code = StringUtils.substringAfter(content, "bind");
			
			String text = shopService.bindShopNotifyUser(code, "aaa");
			System.out.println(text);
		}
	}
	
}