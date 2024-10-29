package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.MessageDAO;
import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.model.vo.MessageVO;
import kr.kh.petvely.model.vo.PostVO;

@Service
public class MessageService {

	@Autowired
	MessageDAO messageDao;
	
	public List<MessageVO> getMessageList(int me_num) {

		return messageDao.selectMessages(me_num);
	}
	public List<MemberVO> getMemberIds(int senderNum) {
		
		return messageDao.selectMemberIds(senderNum);
	}
	public boolean sendMessage(MessageVO message) {
		try {
			messageDao.insertMessage(message);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Integer getreceiverId(String receiverId) {
		return messageDao.selectReceiverId(receiverId);
	}
	public Integer getsenderId(String senderId) {
		return messageDao.selectsenderId(senderId);
	}
	
	
	public PostVO getPostUserNum(int po_num) {
		
		return messageDao.selectPostUserNum(po_num);
	}
	public boolean MarketMessageSend(MessageVO message) {
		try {
			messageDao.insertMarketMessage(message);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}
	public MessageVO getMessageDetail(int mes_num) {
			
		return messageDao.selectMessageDetail(mes_num);
	}


	@Scheduled(cron = "0 0 0 * * ?")
	public void deleteMessageExpiration() {
		messageDao.deleteMessageExpiration();
	}



}
