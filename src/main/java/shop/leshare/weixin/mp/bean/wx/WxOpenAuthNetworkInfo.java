package shop.leshare.weixin.mp.bean.wx;

import me.chanjar.weixin.common.util.json.WxGsonBuilder;

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
 *         CreateTime：8/19/17
 */
public class WxOpenAuthNetworkInfo implements Serializable{
	
	private static final long serialVersionUID = 620052180470251244L;
	private String[] RequestDomain;
	private String[] WsRequestDomain;
	private String[] UploadDomain;
	private String[] DownloadDomain;
	
	public String toJson(){
		return WxGsonBuilder.create().toJson(this);
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String[] getRequestDomain() {
		return RequestDomain;
	}
	
	public void setRequestDomain(String[] requestDomain) {
		RequestDomain = requestDomain;
	}
	
	public String[] getWsRequestDomain() {
		return WsRequestDomain;
	}
	
	public void setWsRequestDomain(String[] wsRequestDomain) {
		WsRequestDomain = wsRequestDomain;
	}
	
	public String[] getUploadDomain() {
		return UploadDomain;
	}
	
	public void setUploadDomain(String[] uploadDomain) {
		UploadDomain = uploadDomain;
	}
	
	public String[] getDownloadDomain() {
		return DownloadDomain;
	}
	
	public void setDownloadDomain(String[] downloadDomain) {
		DownloadDomain = downloadDomain;
	}
}
