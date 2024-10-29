package kr.kh.petvely.dao.member;

import java.util.List;

import kr.kh.petvely.model.vo.CommentVO;

public interface MypageDAO {

	List<CommentVO> selectCommentList(int me_num);
	
}
