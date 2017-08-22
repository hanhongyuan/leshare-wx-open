package shop.leshare.weixin.open.service.impl;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import me.chanjar.weixin.mp.api.impl.WxMpServiceAbstractImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import shop.leshare.weixin.open.bean.wx.WxOpenRefreshTokenQuery;
import shop.leshare.weixin.open.bean.wx.WxOpenRefreshTokenResult;
import shop.leshare.weixin.open.manage.RedisStringManage;

/**
 * <p>Title: shop.leshare.weixin.mp.service.impl</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/19/17
 */
public class WxOpenServiceApacheHttpClientImpl extends WxMpServiceAbstractImpl<CloseableHttpClient, HttpHost> {
	
	private Logger logger = LogManager.getLogger(WxOpenServiceApacheHttpClientImpl.class);
	
	private CloseableHttpClient httpClient;
	private HttpHost httpProxy;
	
	private static final String AUTHORIZER_ACCESS_TOKEN_KEY = "AUTHORIZER_ACCESS_TOKEN_KEY";
	private static final String AUTHORIZER_REFRESH_TOKEN_KEY = "AUTHORIZER_REFRESH_TOKEN_KEY";
	private static final String API_AUTHORIZER_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=%s";
	
	@Autowired
	private RedisStringManage redisStringManage;
	
	@Override
	public CloseableHttpClient getRequestHttpClient() {
		return httpClient;
	}
	
	@Override
	public HttpHost getRequestHttpProxy() {
		return httpProxy;
	}
	
	@Override
	public HttpType getRequestType() {
		return HttpType.APACHE_HTTP;
	}
	
	@Override
	public void initHttp() {
		ApacheHttpClientBuilder apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
		this.httpClient = apacheHttpClientBuilder.build();
	}
	
	@Override
	public String getAccessToken(boolean forceRefresh) throws WxErrorException {
		
		logger.info("getAccessToken <<<<<<<<<< ");
		
		Object obj = redisStringManage.getString(this.getWxMpConfigStorage().getAuthorizerAppid() + "AUTHORIZER_ACCESS_TOKEN_KEY");
		
		if(obj == null){
			return getAccessToken(this.getWxMpConfigStorage().getAuthorizerAppid());
		}
		
		return obj == null ? "" : obj.toString();
	}
	
	public String getAccessToken(String appId) throws WxErrorException {
		
		//从redis读取authorizer_refresh_token
		Object refreshTokenObj = redisStringManage.getString(appId + AUTHORIZER_REFRESH_TOKEN_KEY);
		if(refreshTokenObj == null){
			logger.error("refresh_token is expired. appid:{}", appId);
			throw new WxErrorException(WxError.newBuilder().setErrorCode(9999).setErrorMsg("authorizer_refresh_token expired！").build());
		}
		
		WxOpenRefreshTokenQuery refreshTokenQuery = new WxOpenRefreshTokenQuery();
		refreshTokenQuery.setComponent_appid(this.getWxMpConfigStorage().getAppId());
		refreshTokenQuery.setAuthorizer_appid(appId);
		refreshTokenQuery.setAuthorizer_refresh_token(refreshTokenObj.toString());
		
		logger.info("用户:[{}] Access_Token过期， 重新请求, 参数:{}", appId, refreshTokenQuery);
		String json = post(API_AUTHORIZER_TOKEN_URL, refreshTokenQuery.toJson());
		
		WxOpenRefreshTokenResult refreshTokenResult = WxOpenRefreshTokenResult.fromJson(json);
		
		logger.info("refreshAccessToken, data:{}", refreshTokenResult);
		
		//save to redis
		if(!StringUtils.isEmpty(refreshTokenResult.getAuthorizer_access_token())){
			logger.info("用户:{}重新获取Access_Token成功", appId);
			redisStringManage.addString(appId + AUTHORIZER_ACCESS_TOKEN_KEY, 6000L,
					refreshTokenResult.getAuthorizer_access_token());
		}
		
		if(!StringUtils.isEmpty(refreshTokenResult.getAuthorizer_refresh_token())){
			logger.info("用户:{}重新获取Refresh_Access_Token成功", appId);
			redisStringManage.addString(appId + AUTHORIZER_REFRESH_TOKEN_KEY,
					refreshTokenResult.getExpires_in(),
					refreshTokenResult.getAuthorizer_refresh_token());
		}
		
		return refreshTokenResult.getAuthorizer_access_token();
	}
	
}
