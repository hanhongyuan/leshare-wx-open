package shop.leshare.weixin.open.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import shop.leshare.weixin.open.bean.OpenUserNotify;

import java.util.List;

/**
 * <p>Title: shop.leshare.weixin.open.mapper</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/31/17
 */
@Mapper
@Component
public interface WxOpenUserNotifyMapper {

	@Select("select id, app_id, open_id from wx_open_user_notify where app_id=#{app_id}")
	@Results({
			@Result(property = "appId", column = "app_id"),
			@Result(property = "openId", column = "open_id")
	})
	List<OpenUserNotify> findByAppid(@Param("app_id") String appId);
	
	@Insert("insert into wx_open_user_notify(app_id, open_id, create_time) values(#{appId}, #{openId}, now())")
	void addUserNotify(OpenUserNotify openUserNotify);
}
