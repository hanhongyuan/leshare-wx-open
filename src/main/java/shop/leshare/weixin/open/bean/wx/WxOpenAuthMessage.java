package shop.leshare.weixin.open.bean.wx;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import shop.leshare.weixin.open.utils.XStreamTransformer;

import java.io.InputStream;
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
@XStreamAlias("xml")
public class WxOpenAuthMessage implements Serializable{
	
	private static final long serialVersionUID = 2462306492465083506L;
	
	@XStreamAlias("AppId")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String appId;
	
	@XStreamAlias("CreateTime")
	private String createTime;
	
	@XStreamAlias("InfoType")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String infoType;
	
	@XStreamAlias("AuthorizerAppid")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String authorizerAppid;
	
	@XStreamAlias("AuthorizationCode")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String authorizationCode;
	
	@XStreamAlias("AuthorizationCodeExpiredTime")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String authorizationCodeExpiredTime;
	
	public static WxOpenAuthMessage fromXml(String xml){
		return XStreamTransformer.fromXml(WxOpenAuthMessage.class, xml);
	}
	
	public static WxOpenAuthMessage fromXml(InputStream is){
		return XStreamTransformer.fromXml(WxOpenAuthMessage.class, is);
	}
	
	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getInfoType() {
		return infoType;
	}
	
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	
	public String getAuthorizerAppid() {
		return authorizerAppid;
	}
	
	public void setAuthorizerAppid(String authorizerAppid) {
		this.authorizerAppid = authorizerAppid;
	}
	
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	
	public String getAuthorizationCodeExpiredTime() {
		return authorizationCodeExpiredTime;
	}
	
	public void setAuthorizationCodeExpiredTime(String authorizationCodeExpiredTime) {
		this.authorizationCodeExpiredTime = authorizationCodeExpiredTime;
	}
	
	@Override
	public String toString() {
		return "WxOpenAuthMessage{" +
				"appId='" + appId + '\'' +
				", createTime='" + createTime + '\'' +
				", infoType='" + infoType + '\'' +
				", authorizerAppid='" + authorizerAppid + '\'' +
				", authorizationCode='" + authorizationCode + '\'' +
				", authorizationCodeExpiredTime='" + authorizationCodeExpiredTime + '\'' +
				'}';
	}
}
