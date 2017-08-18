package shop.leshare.weixin.mp;

import shop.leshare.weixin.mp.bean.wx.WxOpenAccessTokenQuery;
import shop.leshare.weixin.mp.bean.wx.WxOpenAccessTokenResult;
import shop.leshare.weixin.mp.bean.wx.WxOpenPreAuthCodeQuery;
import shop.leshare.weixin.mp.bean.wx.WxOpenPreAuthCodeResult;

/**
 * <p>Title: shop.leshare.weixin.mp</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/10/17
 */
public class T {
	
	public static void main(String[] args) {
		
		WxOpenAccessTokenQuery query = new WxOpenAccessTokenQuery();
		query.setComponent_appid("a");
		query.setComponent_appsecret("b");
		query.setComponent_verify_ticket("c");
		
		System.out.println(query.toJson());
		
		String json = "{\n" +
				"\"component_access_token\":\"61W3mEpU66027wgNZ_MhGHNQDHnFATkDa9-2llqrMBjUwxRSNPbVsMmyD-yq8wZETSoE5NQgecigDrSHkPtIYA\", \n" +
				"\"expires_in\":7200\n" +
				"}";
		
		WxOpenAccessTokenResult result = WxOpenAccessTokenResult.fromJson(json);
		System.out.println(result);
		
		System.out.println("-------------------------------");
		
		WxOpenPreAuthCodeQuery query1 = new WxOpenPreAuthCodeQuery();
		query1.setComponent_appid("a");
		System.out.println(query1.toJson());
		
		String preAuthResultJson = "{\n" +
				"\"pre_auth_code\":\"Cx_Dk6qiBE0Dmx4EmlT3oRfArPvwSQ-oa3NL_fwHM7VI08r52wazoZX2Rhpz1dEw\",\n" +
				"\"expires_in\":600\n" +
				"}";
		System.out.println(WxOpenPreAuthCodeResult.fromJson(preAuthResultJson));
	}
}
