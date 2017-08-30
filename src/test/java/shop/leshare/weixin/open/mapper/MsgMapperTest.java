package shop.leshare.weixin.open.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.leshare.weixin.open.bean.MpMsg;

import java.util.List;

import static org.junit.Assert.*;

/**
 * <p>Title: shop.leshare.weixin.open.mapper</p>
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
public class MsgMapperTest {
	
	@Autowired
	private MsgMapper msgMapper;
	
	@Test
	public void addMsg() throws Exception {
		
		MpMsg msg = new MpMsg();
		msg.setUsername("test");
		msg.setOpenId("test");
		msg.setType("text");
		msg.setMsgId("msgId");
		msg.setMsgTime("2017-08-30 10:27:30");
		msg.setContent("test");
		msg.setMediaId("mediaId");
		msg.setFormat("format");
		msg.setRecognition("recognition");
		msg.setThumbMediaId("thumbMediaId");
		
		msgMapper.addMsg(msg);
	}
	
	@Test
	public void findMsg() throws Exception {
		List<MpMsg> msgs = msgMapper.findMsg("test", "test");
		msgs.forEach(System.out::println);
	}
	
}