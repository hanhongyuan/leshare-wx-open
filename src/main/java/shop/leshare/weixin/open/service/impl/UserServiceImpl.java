package shop.leshare.weixin.open.service.impl;

import com.github.binarywang.java.emoji.EmojiConverter;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.leshare.common.entity.Result;
import shop.leshare.common.utils.EmptyCheckUtils;
import shop.leshare.weixin.open.bean.MpUser;
import shop.leshare.weixin.open.bean.OpenUserNotify;
import shop.leshare.weixin.open.mapper.WxMpUserMapper;
import shop.leshare.weixin.open.mapper.WxOpenUserNotifyMapper;
import shop.leshare.weixin.open.service.UserService;

import java.util.List;

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
	
	@Autowired
	private WxOpenUserNotifyMapper wxOpenUserNotifyMapper;
	
	/**
	 * 查找用户信息
	 *
	 * @param openId
	 * @return
	 */
	@Override
	public MpUser findUserByOpenId(String openId) {
		return wxMpUserMapper.findUser(openId);
	}
	
	/**
	 * 新增微信公众号用户
	 *
	 * @param user
	 * @return
	 */
	@Override
	public Result addUser(WxMpUser user, String appId) {
		
		//转换emoji表情
		logger.info("转换前: {}", user.getNickname());
		user.setNickname(EmojiConverter.getInstance().toHtml(user.getNickname()));
		logger.info("转换后: {}", user.getNickname());
		
		MpUser mpUser = wxMpUserMapper.findUser(user.getOpenId());
		if(mpUser == null){
			wxMpUserMapper.addUser(MpUser.fromWxUser(user, appId));
			logger.info("AppId:{} 有新的关注用户, {}", appId, user);
		}else {
			wxMpUserMapper.updateUser(MpUser.fromWxUser(user, appId));
			logger.info("AppId:{} 有重新关注用户, {}", appId, user);
		}
		
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
	
	/**
	 * 从微信服务器同步一次全量用户信息
	 *
	 * @param userList
	 * @return
	 */
	@Override
	public Result addUserList(List<WxMpUser> userList) {
		
		if(EmptyCheckUtils.isEmpty(userList)) return Result.fail();
		
		
		
		return null;
	}
	
	/**
	 * 查找公众号通知列表
	 *
	 * @param appId
	 * @return
	 */
	@Override
	public List<OpenUserNotify> findNotifyUsers(String appId) {
		
		List<OpenUserNotify> notifyList = wxOpenUserNotifyMapper.findByAppid(appId);
		
		logger.debug("根据appId:({})查找通知列表, count :{}", appId, notifyList == null ? 0 : notifyList.size());
		
		return notifyList;
	}
	
	
}
