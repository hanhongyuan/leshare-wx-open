package shop.leshare.weixin.open.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.leshare.common.entity.Shop;
import shop.leshare.common.entity.ShopMpNotify;

import static org.junit.Assert.*;

/**
 * <p>Title: shop.leshare.weixin.open.mapper</p>
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
public class ShopMapperTest {
	
	@Test
	public void findShopMpNotify() throws Exception {
		ShopMpNotify notify = shopMapper.findShopMpNotify(14, "o3o8C02Zy0DWMjqoxbtAzhvDUv0Y");
		
		System.out.println("result: " + notify);
	}
	
	@Autowired
	private ShopMapper shopMapper;
	
	@Test
	public void findShopIdByCode() throws Exception {
		
		Shop shop = shopMapper.findShopIdByCode("eemTgbKrhqiYKMFowcBCQ1Zr7G13bWUT");
		System.out.println("name = " + shop.getName());
	}
	
	@Test
	public void saveMpNotifyUser() throws Exception {
	
		shopMapper.saveMpNotifyUser(14, "afadfasd");
	}
	
}