package shop.leshare.weixin.mp.bean;

import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * <p>Title: shop.leshare.weixin.mp.bean</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/10/17
 */
public class WxOpenPreAuthCodeResult implements Serializable{
	
	private static final long serialVersionUID = -4246610332960683249L;
	private String pre_auth_code;
	private long expires_in;
	
	public String getPre_auth_code() {
		return pre_auth_code;
	}
	
	public void setPre_auth_code(String pre_auth_code) {
		this.pre_auth_code = pre_auth_code;
	}
	
	public long getExpires_in() {
		return expires_in;
	}
	
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}
	
	public static WxOpenPreAuthCodeResult fromJson(String json){
		return WxGsonBuilder.INSTANCE.create().fromJson(json, WxOpenPreAuthCodeResult.class);
	}
	
	@Override
	public String toString() {
		return "WxOpenPreAuthCodeResult{" +
				"pre_auth_code='" + pre_auth_code + '\'' +
				", expires_in=" + expires_in +
				'}';
	}
}
