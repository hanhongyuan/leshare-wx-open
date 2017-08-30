package shop.leshare.weixin.open.service;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import shop.leshare.common.entity.Result;

/**
 * <p>Title: shop.leshare.weixin.open.service</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/30/17
 */
public interface MsgService {
	
	/**
	 * 保存用户发来的消息
	 * @param msg
	 * @return
	 */
	Result addMsg(WxMpXmlMessage msg);
}
