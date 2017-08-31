package shop.leshare.weixin.open.service;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import shop.leshare.common.entity.Result;
import shop.leshare.weixin.open.bean.MpUser;
import shop.leshare.weixin.open.bean.OpenUserNotify;

import java.util.List;

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
	 * 查找用户信息
	 * @param openId
	 * @return
	 */
	MpUser findUserByOpenId(String openId);
	
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
	
	/**
	 * 从微信服务器同步一次全量用户信息
	 * @return
	 */
	Result addUserList(List<WxMpUser> userList);
	
	/**
	 * 查找公众号通知列表
	 * @param appId
	 * @return
	 */
	List<OpenUserNotify> findNotifyUsers(String appId);
}
