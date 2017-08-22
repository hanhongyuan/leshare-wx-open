package shop.leshare.weixin.open.bean.wx;

import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.Arrays;

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
public class WxOpenAuthMiniProgramInfo implements Serializable{
	
	private static final long serialVersionUID = -278527670149820890L;
	private WxOpenAuthNetworkInfo network;
	private WxOpenAuthMiniprogCate[] categories;
	private int visit_status;
	
	public String toJson(){
		return WxGsonBuilder.create().toJson(this);
	}
	
	public WxOpenAuthNetworkInfo getNetwork() {
		return network;
	}
	
	public void setNetwork(WxOpenAuthNetworkInfo network) {
		this.network = network;
	}
	
	public WxOpenAuthMiniprogCate[] getCategories() {
		return categories;
	}
	
	public void setCategories(WxOpenAuthMiniprogCate[] categories) {
		this.categories = categories;
	}
	
	public int getVisit_status() {
		return visit_status;
	}
	
	public void setVisit_status(int visit_status) {
		this.visit_status = visit_status;
	}
	
	@Override
	public String toString() {
		return "WxOpenAuthMiniProgramInfo{" +
				"network=" + network +
				", categories=" + Arrays.toString(categories) +
				", visit_status=" + visit_status +
				'}';
	}
}
