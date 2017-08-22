package shop.leshare.weixin.open.bean.wx;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: shop.leshare.weixin.mp.bean.wx</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/18/17
 */
public class WxOpenAuthInfoDetail implements Serializable{
	
	private static final long serialVersionUID = 7343072795733663227L;
	private String authorizer_appid;
	private String authorizer_refresh_token;
	private List<WxOpenAuthFuncInfo> func_info;
	
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
	
	public List<WxOpenAuthFuncInfo> getFunc_info() {
		return func_info;
	}
	
	public void setFunc_info(List<WxOpenAuthFuncInfo> func_info) {
		this.func_info = func_info;
	}
	
	public String joinFuncInfo(){
		
		String str = "";
		for (WxOpenAuthFuncInfo funcInfo : func_info) {
			str += funcInfo.getFuncscope_category().getId() + "#";
		}
		return StringUtils.substringBeforeLast(str, "#");
	}
	
	@Override
	public String toString() {
		return "WxOpenAuthInfoDetail{" +
				"authorizer_appid='" + authorizer_appid + '\'' +
				", authorizer_refresh_token='" + authorizer_refresh_token + '\'' +
				", func_info=" + func_info +
				'}';
	}
}
