package shop.leshare.weixin.mp.bean.wx;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import shop.leshare.weixin.mp.utils.XStreamTransformer;

import java.io.InputStream;
import java.io.Serializable;

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
@XStreamAlias("xml")
public class WxOpenNotice implements Serializable{
	
	private static final long serialVersionUID = -3592134815254413552L;
	
	@XStreamAlias("AppId")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String appId;
	
	@XStreamAlias("InfoType")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String typeInfo;
	
	public static WxOpenNotice fromXml(String xml) {
		
		return XStreamTransformer.fromXml(WxOpenNotice.class, xml);
	}
	
	public static WxOpenNotice fromXml(InputStream is) {
		return XStreamTransformer.fromXml(WxOpenNotice.class, is);
	}
	
	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getTypeInfo() {
		return typeInfo;
	}
	
	public void setTypeInfo(String typeInfo) {
		this.typeInfo = typeInfo;
	}
	
	@Override
	public String toString() {
		return "WxOpenNotice{" +
				"appId='" + appId + '\'' +
				", typeInfo='" + typeInfo + '\'' +
				'}';
	}
}
