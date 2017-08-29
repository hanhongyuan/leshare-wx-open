package shop.leshare.weixin.open.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.leshare.common.entity.Result;
import shop.leshare.common.entity.Shop;
import shop.leshare.common.entity.ShopMpNotify;
import shop.leshare.weixin.open.mapper.ShopMapper;
import shop.leshare.weixin.open.service.ShopService;

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
@Service
public class ShopServiceImpl implements ShopService{
	
	private Logger logger = LogManager.getLogger(ShopServiceImpl.class);
	
	@Autowired
	private ShopMapper shopMapper;
	
	/**
	 * 通过回复公众号进行绑定商户订单通知
	 *
	 * @param code
	 * @return
	 */
	@Override
	public String bindShopNotifyUser(String code, String openId) {
		
		logger.info("通过回复公众号进行绑定商户订单通知, shopCode:{}, openId:{}", code, openId);
		
		Shop shop = shopMapper.findShopIdByCode(code);
		
		if(shop == null) {
			logger.error("openId:{} 请求的店铺code:({})不存在", openId, code);
			return "订单通知绑定失败，错误的请求码, 请联系客服(15960163650)处理.";
		}
		
		ShopMpNotify shopMpNotify = shopMapper.findShopMpNotify(shop.getId(), openId);
		
		if(shopMpNotify == null){
			shopMapper.saveMpNotifyUser(shop.getId(), openId);
		}else {
			return "您已经绑定过订单通知了, 请勿重复操作.";
		}
		
		logger.info("为用户:{} 店铺:{} 绑定订单通知", openId, shop.getName());
		
		return "您已成功绑定【" + shop.getName() + "】订单通知.";
	}
}
