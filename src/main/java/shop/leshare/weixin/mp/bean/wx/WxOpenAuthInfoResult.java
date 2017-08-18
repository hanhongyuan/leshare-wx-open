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
public class WxOpenAuthInfoResult implements Serializable{
	
	private static final long serialVersionUID = -6586585594208448857L;
	private WxOpenAuthInfoBase authorizer_info;
	private WxOpenAuthInfoDetail authorization_info;
	
	public static WxOpenAuthInfoResult fromJson(String json){
		return WxGsonBuilder.INSTANCE.create().fromJson(json, WxOpenAuthInfoResult.class);
	}
	
	public WxOpenAuthInfoBase getAuthorizer_info() {
		return authorizer_info;
	}
	
	public void setAuthorizer_info(WxOpenAuthInfoBase authorizer_info) {
		this.authorizer_info = authorizer_info;
	}
	
	public WxOpenAuthInfoDetail getAuthorization_info() {
		return authorization_info;
	}
	
	public void setAuthorization_info(WxOpenAuthInfoDetail authorization_info) {
		this.authorization_info = authorization_info;
	}
	
	@Override
	public String toString() {
		return "WxOpenAuthInfoResult{" +
				"authorizer_info=" + authorizer_info +
				", authorization_info=" + authorization_info +
				'}';
	}
}
