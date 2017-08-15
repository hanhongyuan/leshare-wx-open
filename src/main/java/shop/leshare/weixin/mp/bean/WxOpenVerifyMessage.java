package shop.leshare.weixin.mp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import shop.leshare.weixin.mp.utils.XStreamTransformer;

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
public class WxOpenVerifyMessage implements Serializable{
	
	private static final long serialVersionUID = -199871691467101133L;
	
	@XStreamAlias("AppId")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String appId;
	
	@XStreamAlias("CreateTime")
	private String createTime;
	
	@XStreamAlias("InfoType")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String infoType;
	
	@XStreamAlias("ComponentVerifyTicket")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String componentVerifyTicket;
	
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
	
	public String getComponentVerifyTicket() {
		return componentVerifyTicket;
	}
	
	public void setComponentVerifyTicket(String componentVerifyTicket) {
		this.componentVerifyTicket = componentVerifyTicket;
	}
	
	public static WxOpenVerifyMessage fromXml(String xml) {
		
		return XStreamTransformer.fromXml(WxOpenVerifyMessage.class, xml);
	}
	
	public static WxOpenVerifyMessage fromXml(InputStream is) {
		return XStreamTransformer.fromXml(WxOpenVerifyMessage.class, is);
	}
	
	@Override
	public String toString() {
		return "WxOpenVerifyMessage{" +
				"appId='" + appId + '\'' +
				", createTime='" + createTime + '\'' +
				", infoType='" + infoType + '\'' +
				", componentVerifyTicket='" + componentVerifyTicket + '\'' +
				'}';
	}
}
