package shop.leshare.weixin.open.bean;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * <p>Title: shop.leshare.weixin.open.bean</p>
 * <p/>
 * <p>
 *  公众号用户实体
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/22/17
 */
public class MpUser implements Serializable{
	
	private static final long serialVersionUID = -562841694915168706L;
	
	private int id;
	private String app_id;
	private String open_id;
	private String nick_name;
	private String sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String head_img;
	private String sub_time;
	private String union_id;
	private String remark;
	private String tag_id;
	private int group_id;
	private int subscribe;
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
	
	public String getOpen_id() {
		return open_id;
	}
	
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	
	public String getNick_name() {
		return nick_name;
	}
	
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getHead_img() {
		return head_img;
	}
	
	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}
	
	public String getSub_time() {
		return sub_time;
	}
	
	public void setSub_time(String sub_time) {
		this.sub_time = sub_time;
	}
	
	public String getUnion_id() {
		return union_id;
	}
	
	public void setUnion_id(String union_id) {
		this.union_id = union_id;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getTag_id() {
		return tag_id;
	}
	
	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}
	
	public int getGroup_id() {
		return group_id;
	}
	
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	
	public int getSubscribe() {
		return subscribe;
	}
	
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
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
		return "MpUser{" +
				"id=" + id +
				", app_id='" + app_id + '\'' +
				", open_id='" + open_id + '\'' +
				", nick_name='" + nick_name + '\'' +
				", sex='" + sex + '\'' +
				", language='" + language + '\'' +
				", city='" + city + '\'' +
				", province='" + province + '\'' +
				", country='" + country + '\'' +
				", head_img='" + head_img + '\'' +
				", sub_time='" + sub_time + '\'' +
				", union_id='" + union_id + '\'' +
				", remark='" + remark + '\'' +
				", tag_id='" + tag_id + '\'' +
				", group_id=" + group_id +
				", subscribe=" + subscribe +
				", create_time='" + create_time + '\'' +
				", update_time='" + update_time + '\'' +
				'}';
	}
	
	/**
	 * 将微信用户的实体转成数据库实体
	 * @param wxMpUser
	 * @param appId
	 * @return
	 */
	public static MpUser fromWxUser(WxMpUser wxMpUser, String appId){
		
		MpUser mpUser = new MpUser();
		mpUser.setApp_id(appId);
		mpUser.setOpen_id(wxMpUser.getOpenId());
		mpUser.setNick_name(wxMpUser.getNickname());
		mpUser.setSex(wxMpUser.getSex());
		mpUser.setLanguage(wxMpUser.getLanguage());
		mpUser.setCity(wxMpUser.getSex());
		mpUser.setProvince(wxMpUser.getProvince());
		mpUser.setCountry(wxMpUser.getCountry());
		mpUser.setHead_img(wxMpUser.getHeadImgUrl());
		
		DateTime dt = new DateTime(wxMpUser.getSubscribeTime() * 1000);
		mpUser.setSub_time(dt.toString("yyyy-MM-dd HH:mm:ss"));
		
		mpUser.setUnion_id(wxMpUser.getUnionId());
		mpUser.setRemark(wxMpUser.getRemark());
		mpUser.setTag_id(StringUtils.join(wxMpUser.getTagIds(), ","));
		mpUser.setGroup_id(wxMpUser.getGroupId());
		
		return mpUser;
	}
}
