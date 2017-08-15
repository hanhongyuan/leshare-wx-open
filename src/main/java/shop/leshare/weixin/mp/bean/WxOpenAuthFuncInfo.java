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
public class WxOpenAuthFuncInfo implements Serializable{
	
	private static final long serialVersionUID = 2232294090059619142L;
	
	private WxOpenAuthFuncscopeCategory funcscope_category;
	
	public WxOpenAuthFuncscopeCategory getFuncscope_category() {
		return funcscope_category;
	}
	
	public void setFuncscope_category(WxOpenAuthFuncscopeCategory funcscope_category) {
		this.funcscope_category = funcscope_category;
	}
	
	@Override
	public String toString() {
		return "FuncInfo{" +
				"funcscope_category=" + funcscope_category +
				'}';
	}
}
