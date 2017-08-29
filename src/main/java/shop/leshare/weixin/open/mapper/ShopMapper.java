package shop.leshare.weixin.open.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import shop.leshare.common.entity.Shop;
import shop.leshare.common.entity.ShopMpNotify;

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
@Mapper
@Component
public interface ShopMapper {

	@Select("select id, `name` from shop where `code` = #{code}")
	Shop findShopIdByCode(String code);

	@Select("SELECT shop_id, open_id from shop_mp_notify where shop_id=#{shop_id} and open_id=#{open_id}")
	@Results({
			@Result(property = "shopId", column = "shop_id"),
			@Result(property = "openId", column = "open_id")
	})
	ShopMpNotify findShopMpNotify(@Param("shop_id") int shopId, @Param("open_id") String openId);
	
	@Insert("insert into shop_mp_notify(shop_id, open_id, create_time, update_time) VALUES (#{shop_id}, #{open_id}, now(), now())")
	void saveMpNotifyUser(@Param("shop_id") int shopId, @Param("open_id") String openId);
	
}
