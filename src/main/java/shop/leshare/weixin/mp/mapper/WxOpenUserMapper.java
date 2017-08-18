package shop.leshare.weixin.mp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import shop.leshare.weixin.mp.bean.WxOpenUser;

import java.util.List;

/**
 * <p>Title: shop.leshare.weixin.mp.mapper</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/18/17
 */
@Mapper
@Component
public interface WxOpenUserMapper {
	
	/**
	 * 查找开放平台已授权或授权成功的用户
	 * @return
	 */
	@Select("select * from wx_open_user where is_use=1")
	List<WxOpenUser> findUserList();

	@Insert("insert into wx_open_user(\n" +
			"  app_id,\n" +
			"  nick_name,\n" +
			"  head_img,\n" +
			"  service_type_info,\n" +
			"  verify_type_info,\n" +
			"  user_name,\n" +
			"  signature,\n" +
			"  principal_name,\n" +
			"  alias,\n" +
			"  business_info,\n" +
			"  qrcode_url,\n" +
			"  authorization_info,\n" +
			"  func_info,\n" +
			"  type,\n" +
			"  is_use,\n" +
			"  create_time,\n" +
			"  update_time)\n" +
			"VALUES(\n" +
			"  #{app_id},\n" +
			"  #{nick_name},\n" +
			"  #{head_img},\n" +
			"  #{service_type_info},\n" +
			"  #{verify_type_info},\n" +
			"  #{user_name},\n" +
			"  #{signature},\n" +
			"  #{principal_name},\n" +
			"  #{alias},\n" +
			"  #{business_info},\n" +
			"  #{qrcode_url},\n" +
			"  #{authorization_info},\n" +
			"  #{func_info},\n" +
			"  #{type},\n" +
			"  #{is_use},\n" +
			"  now(),\n" +
			"  now())")
	void addUser(WxOpenUser wxOpenUser);
}