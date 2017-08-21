package shop.leshare.weixin.mp.service.impl;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import me.chanjar.weixin.mp.api.impl.AbstractWxMpServiceImpl;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import shop.leshare.weixin.mp.manage.RedisStringManage;

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
public class WxOpenServiceApacheHttpClientImpl extends AbstractWxMpServiceImpl<CloseableHttpClient, HttpHost> {
	
	private CloseableHttpClient httpClient;
	private HttpHost httpProxy;
	
	private static final String AUTHORIZER_ACCESS_TOKEN_KEY = "AUTHORIZER_ACCESS_TOKEN_KEY";
	
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
		
		Object obj = redisStringManage.getString(AUTHORIZER_ACCESS_TOKEN_KEY);
		
		return obj == null ? "" : obj.toString();
	}
	
}
