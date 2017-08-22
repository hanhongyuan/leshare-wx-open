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
public class WxOpenAuthBusinessInfo implements Serializable{
	
	private static final long serialVersionUID = 6953849613936644811L;
	private int open_store;//是否开通微信门店功能
	private int open_scan;//是否开通微信扫商品功能
	private int open_pay;//是否开通微信支付功能
	private int open_card;//是否开通微信卡券功能
	private int open_shake;//是否开通微信摇一摇功能
	
	public int getOpen_store() {
		return open_store;
	}
	
	public void setOpen_store(int open_store) {
		this.open_store = open_store;
	}
	
	public int getOpen_scan() {
		return open_scan;
	}
	
	public void setOpen_scan(int open_scan) {
		this.open_scan = open_scan;
	}
	
	public int getOpen_pay() {
		return open_pay;
	}
	
	public void setOpen_pay(int open_pay) {
		this.open_pay = open_pay;
	}
	
	public int getOpen_card() {
		return open_card;
	}
	
	public void setOpen_card(int open_card) {
		this.open_card = open_card;
	}
	
	public int getOpen_shake() {
		return open_shake;
	}
	
	public void setOpen_shake(int open_shake) {
		this.open_shake = open_shake;
	}
	
	@Override
	public String toString() {
		return "WxOpenAuthBusinessInfo{" +
				"open_store=" + open_store +
				", open_scan=" + open_scan +
				", open_pay=" + open_pay +
				", open_card=" + open_card +
				", open_shake=" + open_shake +
				'}';
	}
}
