package shop.leshare.weixin.open.service.impl;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.leshare.weixin.open.service.MsgService;

import static org.junit.Assert.*;

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
@SpringBootTest
@RunWith(SpringRunner.class)
public class MsgServiceImplTest {
	
	@Autowired
	private MsgService msgService;
	
	@Test
	public void addMsg() throws Exception {
		
		WxMpXmlMessage msg = new WxMpXmlMessage();
		msg.setToUser("gh_14a319bcec72");
		msg.setFromUser("o3o8C02Zy0DWMjqoxbtAzhvDUv0Y");
		msg.setCreateTime(1504103524L);
		msg.setMsgType("text");
		msg.setContent("22");
		msg.setMsgId(6460075445809702712L);
		
		msgService.addMsg(msg);
		
	}
	
}