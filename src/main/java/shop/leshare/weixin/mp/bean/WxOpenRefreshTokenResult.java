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
 *         CreateTime：8/16/17
 */
public class WxOpenRefreshTokenResult implements Serializable{
	
	private static final long serialVersionUID = -3108056450144632601L;
	private String authorizer_access_token;//授权方令牌
	private long expires_in;//有效期，为2小时
	private String authorizer_refresh_token;//刷新令牌
	
	public static WxOpenRefreshTokenResult fromJson(String json){
		return WxGsonBuilder.INSTANCE.create().fromJson(json, WxOpenRefreshTokenResult.class);
	}
	
	public String getAuthorizer_access_token() {
		return authorizer_access_token;
	}
	
	public void setAuthorizer_access_token(String authorizer_access_token) {
		this.authorizer_access_token = authorizer_access_token;
	}
	
	public long getExpires_in() {
		return expires_in;
	}
	
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}
	
	public String getAuthorizer_refresh_token() {
		return authorizer_refresh_token;
	}
	
	public void setAuthorizer_refresh_token(String authorizer_refresh_token) {
		this.authorizer_refresh_token = authorizer_refresh_token;
	}
}
