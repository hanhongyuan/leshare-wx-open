package shop.leshare.weixin.open.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import shop.leshare.weixin.open.bean.MpMsg;

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
 *         CreateTime：8/30/17
 */
@Mapper
@Component
public interface MsgMapper {

	@Insert("INSERT INTO wx_mp_msg(username, open_id, type, msg_id, msg_time, content, media_id, format, recognition, thumb_media_id, create_time)\n" +
			"VALUES (#{username}, #{openId}, #{type}, #{msgId}, #{msgTime}, #{content}, #{mediaId}, #{format}, #{recognition}, #{thumbMediaId}, now())")
	void addMsg(MpMsg mpMsg);
	
	@Select("SELECT\n" +
			"  username,\n" +
			"  open_id,\n" +
			"  type,\n" +
			"  msg_id,\n" +
			"  date_format(msg_time, '%Y-%m-%d %H:%i:%s') msg_time,\n" +
			"  content,\n" +
			"  media_id,\n" +
			"  format,\n" +
			"  recognition,\n" +
			"  thumb_media_id,\n" +
			"  date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time\n" +
			"FROM wx_mp_msg \n" +
			"WHERE username = #{username} and open_id = #{open_id}")
	@Results({
			@Result(property = "openId", column = "open_id"),
			@Result(property = "msgId", column = "msg_id"),
			@Result(property = "msgTime", column = "msg_time"),
			@Result(property = "mediaId", column = "media_id"),
			@Result(property = "thumbMediaId", column = "thumb_media_id"),
			@Result(property = "createTime", column = "create_time")
	})
	List<MpMsg> findMsg(@Param("username") String username, @Param("open_id") String openId);
}
