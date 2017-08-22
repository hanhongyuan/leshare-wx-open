package shop.leshare.weixin.open.mapper;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * <p>Title: shop.leshare.weixin.open.mapper</p>
 * <p/>
 * <p>
 *  微信公众号用户操作
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/22/17
 */
@Mapper
@Component
public interface WxMpUserMapper {

	@Insert("insert into wx_mp_user(\n" +
			"  app_id, \n" +
			"  open_id, \n" +
			"  nick_name, \n" +
			"  sex, \n" +
			"  language, \n" +
			"  city, \n" +
			"  province, \n" +
			"  country, \n" +
			"  head_img, \n" +
			"  sub_time, \n" +
			"  union_id, \n" +
			"  remark, \n" +
			"  tag_id, \n" +
			"  group_id, \n" +
			"  create_time, \n" +
			"  update_time, \n" +
			"  subscribe)\n" +
			"VALUES (\n" +
			"  #{appId}, \n" +
			"  #{user.openId}, \n" +
			"  #{user.nickname}, \n" +
			"  #{user.sex}, \n" +
			"  #{user.language}, \n" +
			"  #{user.city}, \n" +
			"  #{user.province}, \n" +
			"  #{user.country}, \n" +
			"  #{user.headImgUrl}, \n" +
			"  #{subTime}, \n" +
			"  #{user.unionId}, \n" +
			"  #{user.remark}, \n" +
			"  #{tagidStr}, \n" +
			"  #{user.groupId}, \n" +
			"  now(), \n" +
			"  now(), \n" +
			"  1)\n")
	void addUser(@Param("user") WxMpUser user, @Param("appId") String appId,@Param("subTime") String subTime, @Param("tagidStr") String tagidStr);
	
	/**
	 * 当用户取消订阅时，更新库表
	 * @param openId
	 */
	@Update("update wx_mp_user set subscribe=2, update_time=now() where open_id=#{openId}")
	void unsubscribeUser(String openId);
}
