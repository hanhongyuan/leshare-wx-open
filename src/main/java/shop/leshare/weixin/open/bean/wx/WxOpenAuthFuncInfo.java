package shop.leshare.weixin.open.bean.wx;

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
public class WxOpenAuthFuncInfo implements Serializable{
	
	private static final long serialVersionUID = 2232294090059619142L;
	private WxOpenIDResult funcscope_category;
	private WxOpenAuthConfirmInfo confirm_info;
	
	public WxOpenIDResult getFuncscope_category() {
		return funcscope_category;
	}
	
	public void setFuncscope_category(WxOpenIDResult funcscope_category) {
		this.funcscope_category = funcscope_category;
	}
	
	public WxOpenAuthConfirmInfo getConfirm_info() {
		return confirm_info;
	}

	public void setConfirm_info(WxOpenAuthConfirmInfo confirm_info) {
		this.confirm_info = confirm_info;
	}
	
	@Override
	public String toString() {
		return "WxOpenAuthFuncInfo{" +
				"funcscope_category=" + funcscope_category +
				", confirm_info=" + confirm_info +
				'}';
	}
}
