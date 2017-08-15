package shop.leshare.weixin.mp.bean;

import com.google.common.collect.ImmutableMap;

/**
 * <p>Title: shop.leshare.entity</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：7/4/17
 */
public class LeResponse<T> {
	
	private Integer code;
	private T data;
	
	public LeResponse(Integer code, T data) {
		this.code = code;
		this.data = data;
	}
	
	public static LeResponse create(Integer code, Object data){
		
		if(data instanceof String){
			return new LeResponse(code, ImmutableMap.of("message", data.toString()));
		}
		
		return new LeResponse(code, data);
	}
	
	public Integer getCode() {
		return code;
	}
	
	public T getData() {
		if(data instanceof String){
			return (T)ImmutableMap.of("message", data.toString());
		}
		
		return data;
	}
	
	@Override
	public String toString() {
		return "LeResponse{" +
				"code=" + code +
				", data=" + data +
				'}';
	}
	
}
