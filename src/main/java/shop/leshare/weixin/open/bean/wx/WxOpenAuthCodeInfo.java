package shop.leshare.weixin.open.bean.wx;

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
public class WxOpenAuthCodeInfo implements Serializable{
	
	private static final long serialVersionUID = 3052721360426362865L;
	private String authorizer_appid;
	private String authorizer_access_token;
	private long expires_in;
	private String authorizer_refresh_token;
	private List<WxOpenAuthFuncInfo> func_info;
	
	public String getAuthorizer_appid() {
		return authorizer_appid;
	}
	
	public void setAuthorizer_appid(String authorizer_appid) {
		this.authorizer_appid = authorizer_appid;
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
	
	public List<WxOpenAuthFuncInfo> getFunc_info() {
		return func_info;
	}
	
	public void setFunc_info(List<WxOpenAuthFuncInfo> func_info) {
		this.func_info = func_info;
	}
	
	@Override
	public String toString() {
		return "AuthorizationInfo{" +
				"authorizer_appid='" + authorizer_appid + '\'' +
				", authorizer_access_token='" + authorizer_access_token + '\'' +
				", expires_in=" + expires_in +
				", authorizer_refresh_token='" + authorizer_refresh_token + '\'' +
				", func_info=" + func_info +
				'}';
	}
	
}
