package shop.leshare.weixin.mp.bean.wx;

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
 *         CreateTime：8/18/17
 */
public class WxOpenAuthInfoQuery implements Serializable{
	
	private static final long serialVersionUID = -353793987142907704L;
	private String component_appid;
	private String authorizer_appid;
	
	public String toJson(){
		return WxGsonBuilder.create().toJson(this);
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getComponent_appid() {
		return component_appid;
	}
	
	public void setComponent_appid(String component_appid) {
		this.component_appid = component_appid;
	}
	
	public String getAuthorizer_appid() {
		return authorizer_appid;
	}
	
	public void setAuthorizer_appid(String authorizer_appid) {
		this.authorizer_appid = authorizer_appid;
	}
	
	@Override
	public String toString() {
		return "WxOpenAuthInfoQuery{" +
				"component_appid='" + component_appid + '\'' +
				", authorizer_appid='" + authorizer_appid + '\'' +
				'}';
	}
}
