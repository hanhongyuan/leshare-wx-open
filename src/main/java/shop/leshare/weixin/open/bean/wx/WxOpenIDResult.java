package shop.leshare.weixin.open.bean.wx;

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
public class WxOpenIDResult implements Serializable{
	
	private static final long serialVersionUID = -912660595670462977L;
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "WxOpenIDResult{" +
				"id=" + id +
				'}';
	}
}
