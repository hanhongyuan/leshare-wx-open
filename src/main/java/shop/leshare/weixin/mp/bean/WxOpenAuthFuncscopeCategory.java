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
 *         CreateTime：8/15/17
 */
public class WxOpenAuthFuncscopeCategory implements Serializable{
	
	private static final long serialVersionUID = 444931675284205502L;
	
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "FuncscopeCategory{" +
				"id=" + id +
				'}';
	}
}
