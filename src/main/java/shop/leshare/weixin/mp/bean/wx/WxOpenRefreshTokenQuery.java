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
 *         CreateTime：8/16/17
 */
public class WxOpenRefreshTokenQuery implements Serializable{
	
	private static final long serialVersionUID = 845664478471409650L;
	private String component_appid;//第三方平台appid
	private String authorizer_appid;//授权方appid
	private String authorizer_refresh_token;//授权方的刷新令牌，刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌
	
	public String toJson(){
		return WxGsonBuilder.create().toJson(this);
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
	
	public String getAuthorizer_refresh_token() {
		return authorizer_refresh_token;
	}
	
	public void setAuthorizer_refresh_token(String authorizer_refresh_token) {
		this.authorizer_refresh_token = authorizer_refresh_token;
	}
	
	@Override
	public String toString() {
		return "WxOpenRefreshTokenQuery{" +
				"component_appid='" + component_appid + '\'' +
				", authorizer_appid='" + authorizer_appid + '\'' +
				", authorizer_refresh_token='" + authorizer_refresh_token + '\'' +
				'}';
	}
}
