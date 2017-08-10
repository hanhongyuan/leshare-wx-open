package shop.leshare.weixin.mp.bean;

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
public class WxOpenPreAuthCodeQuery implements Serializable{
	
	private static final long serialVersionUID = -5120544663633018002L;
	private String component_appid;
	
	public String getComponent_appid() {
		return component_appid;
	}
	
	public void setComponent_appid(String component_appid) {
		this.component_appid = component_appid;
	}
	
	public String toJson(){
		return WxGsonBuilder.create().toJson(this);
	}
}
