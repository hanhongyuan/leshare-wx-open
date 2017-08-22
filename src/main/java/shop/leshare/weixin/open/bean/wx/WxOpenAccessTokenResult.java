package shop.leshare.weixin.open.bean.wx;

import me.chanjar.weixin.common.util.json.WxGsonBuilder;

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
public class WxOpenAccessTokenResult implements Serializable{
	
	private static final long serialVersionUID = -8440961784573868242L;
	private String component_access_token;
	private long expires_in;
	
	public String getComponent_access_token() {
		return component_access_token;
	}
	
	public void setComponent_access_token(String component_access_token) {
		this.component_access_token = component_access_token;
	}
	
	public long getExpires_in() {
		return expires_in;
	}
	
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}
	
	public static WxOpenAccessTokenResult fromJson(String json) {
		return WxGsonBuilder.INSTANCE.create().fromJson(json, WxOpenAccessTokenResult.class);
	}
	
	@Override
	public String toString() {
		return "WxOpenAccessTokenResult{" +
				"component_access_token='" + component_access_token + '\'' +
				", expires_in=" + expires_in +
				'}';
	}
}
