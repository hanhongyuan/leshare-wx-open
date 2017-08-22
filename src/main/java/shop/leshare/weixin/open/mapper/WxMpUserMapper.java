package shop.leshare.weixin.open.mapper;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import shop.leshare.weixin.open.bean.MpUser;

import java.util.List;

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

	@Select("select\n" +
			"  id,\n" +
			"  app_id,\n" +
			"  open_id,\n" +
			"  nick_name,\n" +
			"  sex,\n" +
			"  language,\n" +
			"  city,\n" +
			"  province,\n" +
			"  country,\n" +
			"  head_img,\n" +
			"  DATE_FORMAT(sub_time, '%Y-%m-%d %H:%i:%s') sub_time,\n" +
			"  union_id,\n" +
			"  remark,\n" +
			"  tag_id,\n" +
			"  group_id,\n" +
			"  DATE_FORMAT(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
			"  DATE_FORMAT(update_time, '%Y-%m-%d %H:%i:%s') update_time,\n" +
			"  subscribe\n" +
			"from wx_mp_user")
	MpUser findUser(String openId);
	
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
			"  #{app_id}, \n" +
			"  #{open_id}, \n" +
			"  #{nickname}, \n" +
			"  #{sex}, \n" +
			"  #{language}, \n" +
			"  #{city}, \n" +
			"  #{province}, \n" +
			"  #{country}, \n" +
			"  #{head_img}, \n" +
			"  #{sub_time}, \n" +
			"  #{union_id}, \n" +
			"  #{remark}, \n" +
			"  #{tag_id}, \n" +
			"  #{group_id}, \n" +
			"  now(), \n" +
			"  now(), \n" +
			"  1)\n")
	void addUser(MpUser user);
	
	/**
	 * 当用户取消订阅时，更新库表
	 * @param openId
	 */
	@Update("update wx_mp_user set subscribe=2, update_time=now() where open_id=#{openId}")
	void unsubscribeUser(String openId);
	
	/**
	 * 当用户重新关注时
	 * @param user
	 */
	@Update("update wx_mp_user set \n" +
			"  nick_name=#{nick_name}, \n" +
			"  sex=#{sex}, \n" +
			"  language=#{language}, \n" +
			"  city=#{city}, \n" +
			"  province=#{province}, \n" +
			"  country=#{country}, \n" +
			"  head_img=#{head_img}, \n" +
			"  sub_time=#{sub_time}, \n" +
			"  union_id=#{union_id}, \n" +
			"  remark=#{remark}, \n" +
			"  tag_id=#{tag_id}, \n" +
			"  group_id=#{group_id}, \n" +
			"  subscribe=1 \n" +
			"where open_id=#{open_id}")
	void updateUser(MpUser user);
}
