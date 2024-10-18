package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.model.vo.MessageVO;

public interface MessageDAO {

	boolean insertMessage(MessageVO message);

	List<MessageVO> selectMessages(int mes_num);

	List<MemberVO> selectMemberIds(int senderNum);

	Integer selectReceiverId(String receiverId);

	Integer selectsenderId(String senderId);

	List<MarketPostVO> selectPostUserId(Integer postUser);

	MarketPostVO selectMarketPostUserId();


	
}
