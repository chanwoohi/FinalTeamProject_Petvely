package kr.kh.petvely.dao.member;

import java.util.List;

import kr.kh.petvely.model.vo.CommentVO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.PostVO;

public interface MypageDAO {

	List<CommentVO> selectCommentList(int me_num);

	List<CommunityVO> selectCommunityList();

	List<PostVO> selectPostList(int co_num, int me_num);
	
}
