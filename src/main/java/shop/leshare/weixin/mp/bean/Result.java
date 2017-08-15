package shop.leshare.weixin.mp.bean;

import java.io.Serializable;

/**
 * <p>Title: shop.leshare.entity</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：6/13/17
 */
public class Result implements Serializable{
	
	private final String fail = "FAIL";
	private final String success = "SUCCESS";
	
	private String result;//操作结果
	
	public Result(int result) {
		
		this.result = success;
		
		if(result <= 0){
			this.result = fail;
		}
	}
	
	public Result(boolean result){
		
		this.result = success;
		
		if(!result){
			this.result = fail;
		}
	}
	
	public String getResult(){
		return result;
	}
	
	public boolean check(){
		return result.equals(success) ? true : false;
	}
	
	public static boolean checkFail(int count){
		return count <=0 ? true : false;
	}
	
	public static Result success(){
		return new Result(true);
	}
	
	public static Result fail(){
		return new Result(false);
	}
	
	@Override
	public String toString() {
		return "Result{" +
				"result=" + result +
				'}';
	}
}
