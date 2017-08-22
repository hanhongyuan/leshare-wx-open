package shop.leshare.weixin.open.service.impl;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.leshare.common.entity.Result;
import shop.leshare.weixin.open.mapper.WxMpUserMapper;
import shop.leshare.weixin.open.service.UserService;

/**
 * <p>Title: shop.leshare.weixin.open.service.impl</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/22/17
 */
@Service
public class UserServiceImpl implements UserService{
	
	private Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private WxMpUserMapper wxMpUserMapper;
	
	/**
	 * 新增微信公众号用户
	 *
	 * @param user
	 * @return
	 */
	@Override
	public Result addUser(WxMpUser user, String appId) {
		
		DateTime dt = new DateTime(user.getSubscribeTime() * 1000);
		
		wxMpUserMapper.addUser(user, appId, dt.toString("yyyy-MM-dd HH:mm:ss"), StringUtils.join(user.getTagIds(), ","));
		
		logger.info("AppId:{} 有新的关注用户, {}", appId, user);
		
		return Result.success();
	}
	
	/**
	 * 用户取消关注公众号
	 *
	 * @param openId
	 * @return
	 */
	@Override
	public Result unsubscribe(String openId) {
		
		wxMpUserMapper.unsubscribeUser(openId);
		logger.info("用户:{} 取消关注.", openId);
		
		return Result.success();
	}
}
