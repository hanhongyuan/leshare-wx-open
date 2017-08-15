package shop.leshare.weixin.mp.bean;

import com.google.common.collect.Lists;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

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
public class WxOpenAuthCodeResult implements Serializable{
	
	private static final long serialVersionUID = 817459069270576338L;
	
	private WxOpenAuthorizationInfo authorization_info;
	
	public String toJson(){
		return WxGsonBuilder.create().toJson(this);
	}
	
	public static WxOpenAuthCodeResult fromJson(String json){
		return WxGsonBuilder.INSTANCE.create().fromJson(json, WxOpenAuthCodeResult.class);
	}
	
	public WxOpenAuthorizationInfo getAuthorization_info() {
		return authorization_info;
	}
	
	public void setAuthorization_info(WxOpenAuthorizationInfo authorization_info) {
		this.authorization_info = authorization_info;
	}
	
	@Override
	public String toString() {
		return "WxOpenAuthCodeResult{" +
				"authorization_info=" + authorization_info +
				'}';
	}
}
