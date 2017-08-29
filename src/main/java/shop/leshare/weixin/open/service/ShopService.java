package shop.leshare.weixin.open.service;

import shop.leshare.common.entity.Result;
import shop.leshare.common.entity.Shop;

/**
 * <p>Title: shop.leshare.weixin.open.service</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/29/17
 */
public interface ShopService {
	
	/**
	 * 通过回复公众号进行绑定商户订单通知
	 * @param code
	 * @return
	 */
	String bindShopNotifyUser(String code, String openId);
	
}
