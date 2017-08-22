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
public class WxOpenAuthConfirmInfo implements Serializable{
	
	private static final long serialVersionUID = 7934530859317739697L;
	private int need_confirm;
	private int already_confirm;
	private int can_confirm;
	
	public int getNeed_confirm() {
		return need_confirm;
	}
	
	public void setNeed_confirm(int need_confirm) {
		this.need_confirm = need_confirm;
	}
	
	public int getAlready_confirm() {
		return already_confirm;
	}
	
	public void setAlready_confirm(int already_confirm) {
		this.already_confirm = already_confirm;
	}
	
	public int getCan_confirm() {
		return can_confirm;
	}
	
	public void setCan_confirm(int can_confirm) {
		this.can_confirm = can_confirm;
	}
	
	@Override
	public String toString() {
		return "WxOpenAuthConfirmInfo{" +
				"need_confirm=" + need_confirm +
				", already_confirm=" + already_confirm +
				", can_confirm=" + can_confirm +
				'}';
	}
}
