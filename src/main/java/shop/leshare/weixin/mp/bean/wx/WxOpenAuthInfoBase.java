package shop.leshare.weixin.mp.bean.wx;

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
public class WxOpenAuthInfoBase implements Serializable{
	
	private static final long serialVersionUID = -1444739664877438756L;
	private String nick_name;
	private String head_img;
	private WxOpenIDResult service_type_info;
	private WxOpenIDResult verify_type_info;
	private String user_name;
	private String principal_name;
	private WxOpenAuthBusinessInfo business_info;
	private String alias;
	private String qrcode_url;
	private int idc;
	private String signature;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getNick_name() {
		return nick_name;
	}
	
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	
	public String getHead_img() {
		return head_img;
	}
	
	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}
	
	public WxOpenIDResult getService_type_info() {
		return service_type_info;
	}
	
	public void setService_type_info(WxOpenIDResult service_type_info) {
		this.service_type_info = service_type_info;
	}
	
	public WxOpenIDResult getVerify_type_info() {
		return verify_type_info;
	}
	
	public void setVerify_type_info(WxOpenIDResult verify_type_info) {
		this.verify_type_info = verify_type_info;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getPrincipal_name() {
		return principal_name;
	}
	
	public void setPrincipal_name(String principal_name) {
		this.principal_name = principal_name;
	}
	
	public WxOpenAuthBusinessInfo getBusiness_info() {
		return business_info;
	}
	
	public void setBusiness_info(WxOpenAuthBusinessInfo business_info) {
		this.business_info = business_info;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getQrcode_url() {
		return qrcode_url;
	}
	
	public void setQrcode_url(String qrcode_url) {
		this.qrcode_url = qrcode_url;
	}
	
	public int getIdc() {
		return idc;
	}
	
	public void setIdc(int idc) {
		this.idc = idc;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@Override
	public String toString() {
		return "WxOpenAuthInfoBase{" +
				"nick_name='" + nick_name + '\'' +
				", head_img='" + head_img + '\'' +
				", service_type_info=" + service_type_info +
				", verify_type_info=" + verify_type_info +
				", user_name='" + user_name + '\'' +
				", principal_name='" + principal_name + '\'' +
				", business_info=" + business_info +
				", alias='" + alias + '\'' +
				", qrcode_url='" + qrcode_url + '\'' +
				", idc=" + idc +
				", signature='" + signature + '\'' +
				'}';
	}
}
