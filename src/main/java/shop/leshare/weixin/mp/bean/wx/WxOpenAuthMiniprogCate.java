package shop.leshare.weixin.mp.bean.wx;

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
public class WxOpenAuthMiniprogCate implements Serializable{
	
	private static final long serialVersionUID = 8550983264083527399L;
	private String first;
	private String second;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getFirst() {
		return first;
	}
	
	public void setFirst(String first) {
		this.first = first;
	}
	
	public String getSecond() {
		return second;
	}
	
	public void setSecond(String second) {
		this.second = second;
	}
	
	@Override
	public String toString() {
		return "WxOpenAuthMiniprogCate{" +
				"first='" + first + '\'' +
				", second='" + second + '\'' +
				'}';
	}
}
