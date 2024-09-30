package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.MessageDAO;
import kr.kh.petvely.model.vo.MessageVO;

@Service
public class MessageService {

	@Autowired
	MessageDAO messageDao;
	public boolean sendMessage(MessageVO message) {
		try {
			messageDao.insertMessage(message);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<MessageVO> getMessageList(int mes_num) {

		return messageDao.selectMessages(mes_num);
	}

}
