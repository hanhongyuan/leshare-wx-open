package shop.leshare.weixin.open.service;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import shop.leshare.common.entity.Result;

/**
 * <p>Title: shop.leshare.weixin.open.service</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/22/17
 */
public interface UserService {
	
	/**
	 * 新增微信公众号用户
	 * @param user
	 * @return
	 */
	Result addUser(WxMpUser user, String appId);
	
	/**
	 * 用户取消关注公众号
	 * @param openId
	 * @return
	 */
	Result unsubscribe(String openId);
}
