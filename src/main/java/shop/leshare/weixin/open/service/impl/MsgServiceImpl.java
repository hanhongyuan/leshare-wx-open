package shop.leshare.weixin.open.service.impl;

import com.github.binarywang.java.emoji.EmojiConverter;
import com.github.binarywang.java.emoji.EmojiUtils;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.leshare.common.entity.Result;
import shop.leshare.weixin.open.bean.MpMsg;
import shop.leshare.weixin.open.mapper.MsgMapper;
import shop.leshare.weixin.open.service.MsgService;

/**
 * <p>Title: shop.leshare.weixin.open.service.impl</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：8/30/17
 */
@Service
public class MsgServiceImpl implements MsgService{
	
	private Logger logger = LogManager.getLogger(MsgServiceImpl.class);
	
	@Autowired
	private MsgMapper msgMapper;
	
	/**
	 * 保存用户发来的消息
	 *
	 * @param msg
	 * @return
	 */
	@Override
	public Result addMsg(WxMpXmlMessage msg) {
		
		logger.info("存储用户发来的消息: {}", msg.toString());
		
		MpMsg mpMsg = new MpMsg();
		mpMsg.setUsername(msg.getToUser());
		mpMsg.setOpenId(msg.getFromUser());
		mpMsg.setType(msg.getMsgType());
		mpMsg.setMsgId(String.valueOf(msg.getMsgId()));
		
		if(!StringUtils.isEmpty(msg.getContent())){
			String content = EmojiConverter.getInstance().toHtml(msg.getContent());
			mpMsg.setContent(content);
		}
		
		DateTime dt = new DateTime(msg.getCreateTime() * 1000);
		
		mpMsg.setMsgTime(dt.toString("yyyy-MM-dd HH:mm:ss"));
		mpMsg.setMediaId(msg.getMediaId());
		mpMsg.setFormat(msg.getFormat());
		mpMsg.setRecognition(msg.getRecognition());
		mpMsg.setThumbMediaId(msg.getThumbMediaId());
		
		msgMapper.addMsg(mpMsg);
		
		return Result.success();
	}
}
