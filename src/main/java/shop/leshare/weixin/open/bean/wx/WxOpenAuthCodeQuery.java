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
 *         CreateTime：8/15/17
 */
public class WxOpenAuthCodeQuery implements Serializable{
	
	private static final long serialVersionUID = -6746878861025075339L;
	private String component_appid;
	private String authorization_code;
	
	public String getComponent_appid() {
		return component_appid;
	}
	
	public void setComponent_appid(String component_appid) {
		this.component_appid = component_appid;
	}
	
	public String getAuthorization_code() {
		return authorization_code;
	}
	
	public void setAuthorization_code(String authorization_code) {
		this.authorization_code = authorization_code;
	}
	
	public String toJson(){
		return WxGsonBuilder.create().toJson(this);
	}
}
