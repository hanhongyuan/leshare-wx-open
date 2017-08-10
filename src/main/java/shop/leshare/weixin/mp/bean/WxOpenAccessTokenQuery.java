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
public class WxOpenAccessTokenQuery implements Serializable{
	
	private static final long serialVersionUID = -8127296167344275480L;
	private String component_appid;
	private String component_appsecret;
	private String component_verify_ticket;
	
	public String getComponent_appid() {
		return component_appid;
	}
	
	public void setComponent_appid(String component_appid) {
		this.component_appid = component_appid;
	}
	
	public String getComponent_appsecret() {
		return component_appsecret;
	}
	
	public void setComponent_appsecret(String component_appsecret) {
		this.component_appsecret = component_appsecret;
	}
	
	public String getComponent_verify_ticket() {
		return component_verify_ticket;
	}
	
	public void setComponent_verify_ticket(String component_verify_ticket) {
		this.component_verify_ticket = component_verify_ticket;
	}
	
	public String toJson() {
		return WxGsonBuilder.create().toJson(this);
	}
	
	@Override
	public String toString() {
		return "WxOpenAccessTokenQuery{" +
				"component_appid='" + component_appid + '\'' +
				", component_appsecret='" + component_appsecret + '\'' +
				", component_verify_ticket='" + component_verify_ticket + '\'' +
				'}';
	}
}
