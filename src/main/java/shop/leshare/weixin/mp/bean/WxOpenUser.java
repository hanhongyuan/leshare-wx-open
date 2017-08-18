package shop.leshare.weixin.mp.bean;

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
public class WxOpenUser implements Serializable{
	
	private static final long serialVersionUID = 4401232916164818866L;
	private int id;
	private String app_id;
	private String nick_name;
	private String head_img;
	private int service_type_info;//授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
	private int verify_type_info;//授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
	private String user_name;//授权方公众号的原始ID
	private String signature;//帐号介绍(小程序专用)
	private String principal_name;//主体名称(小程序)
	private String alias;//授权方公众号所设置的微信号，可能为空
	private String business_info;//open_store:是否开通微信门店功能; open_scan:是否开通微信扫商品功能; open_pay:是否开通微信支付功能; open_card:是否开通微信卡券功能; open_shake:是否开通微信摇一摇功能
	private String qrcode_url;//二维码图片的URL，开发者最好自行也进行保存
	private String authorization_info;//授权信息
	private String func_info;//公众号/小程序 授权给开发者的权限集列表
	private int type;//0:公众号; 1:小程序
	private int is_use;//是否授权, 0: 未授权; 1: 正在授权
	private String create_time;
	private String update_time;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getApp_id() {
		return app_id;
	}
	
	public void setApp_id(String app_id) {
		this.app_id = app_id;
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
	
	public int getService_type_info() {
		return service_type_info;
	}
	
	public void setService_type_info(int service_type_info) {
		this.service_type_info = service_type_info;
	}
	
	public int getVerify_type_info() {
		return verify_type_info;
	}
	
	public void setVerify_type_info(int verify_type_info) {
		this.verify_type_info = verify_type_info;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getPrincipal_name() {
		return principal_name;
	}
	
	public void setPrincipal_name(String principal_name) {
		this.principal_name = principal_name;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getBusiness_info() {
		return business_info;
	}
	
	public void setBusiness_info(String business_info) {
		this.business_info = business_info;
	}
	
	public String getQrcode_url() {
		return qrcode_url;
	}
	
	public void setQrcode_url(String qrcode_url) {
		this.qrcode_url = qrcode_url;
	}
	
	public String getAuthorization_info() {
		return authorization_info;
	}
	
	public void setAuthorization_info(String authorization_info) {
		this.authorization_info = authorization_info;
	}
	
	public String getFunc_info() {
		return func_info;
	}
	
	public void setFunc_info(String func_info) {
		this.func_info = func_info;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getIs_use() {
		return is_use;
	}
	
	public void setIs_use(int is_use) {
		this.is_use = is_use;
	}
	
	public String getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	public String getUpdate_time() {
		return update_time;
	}
	
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	@Override
	public String toString() {
		return "WxOpenUser{" +
				"id=" + id +
				", app_id='" + app_id + '\'' +
				", nick_name='" + nick_name + '\'' +
				", head_img='" + head_img + '\'' +
				", service_type_info=" + service_type_info +
				", verify_type_info=" + verify_type_info +
				", user_name='" + user_name + '\'' +
				", signature='" + signature + '\'' +
				", principal_name='" + principal_name + '\'' +
				", alias='" + alias + '\'' +
				", business_info='" + business_info + '\'' +
				", qrcode_url='" + qrcode_url + '\'' +
				", authorization_info='" + authorization_info + '\'' +
				", func_info='" + func_info + '\'' +
				", type=" + type +
				", is_use=" + is_use +
				", create_time='" + create_time + '\'' +
				", update_time='" + update_time + '\'' +
				'}';
	}
}
