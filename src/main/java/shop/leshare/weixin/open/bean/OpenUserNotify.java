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
 *         CreateTime：8/31/17
 */
public class OpenUserNotify implements Serializable{
	
	private static final long serialVersionUID = -5272827089956344078L;
	private int id;
	private String appId;
	private String openId;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getOpenId() {
		return openId;
	}
	
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@Override
	public String toString() {
		return "OpenUserNotify{" +
				"id=" + id +
				", appId='" + appId + '\'' +
				", openId='" + openId + '\'' +
				'}';
	}
}
