package shop.leshare.weixin.open.bean;

import java.io.Serializable;

/**
 * <p>Title: shop.leshare.weixin.open.bean</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/30/17
 */
public class MpMsg implements Serializable{
	
	private static final long serialVersionUID = 8791356580616683882L;
	private int id;
	private String username;
	private String openId;
	private String type;
	private String msgId;
	private String msgTime;
	private String content;
	private String mediaId;
	private String format;
	private String recognition;
	private String thumbMediaId;
	private String createTime;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getOpenId() {
		return openId;
	}
	
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	public String getMsgTime() {
		return msgTime;
	}
	
	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getRecognition() {
		return recognition;
	}
	
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
	
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return "MpMsg{" +
				"id=" + id +
				", username='" + username + '\'' +
				", openId='" + openId + '\'' +
				", type='" + type + '\'' +
				", msgId='" + msgId + '\'' +
				", msgTime='" + msgTime + '\'' +
				", content='" + content + '\'' +
				", mediaId='" + mediaId + '\'' +
				", format='" + format + '\'' +
				", recognition='" + recognition + '\'' +
				", thumbMediaId='" + thumbMediaId + '\'' +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
