package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.MessageVO;

public interface MessageDAO {

	boolean insertMessage(MessageVO message);

	List<MessageVO> selectMessages(int mes_num);

}
