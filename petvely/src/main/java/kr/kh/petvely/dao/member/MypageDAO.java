package kr.kh.petvely.dao.member;

import java.util.List;

import kr.kh.petvely.model.vo.CommentVO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.GiveAndTakePostVO;
import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.WalkMatePostVO;
import kr.kh.petvely.pagination.PostCriteria;

public interface MypageDAO {

	List<CommentVO> selectCommentList(int me_num);

	List<CommunityVO> selectCommunityList();

	List<PostVO> selectPostList(int co_num, int me_num);

	List<WalkMatePostVO> selectWalkMatePostList(int me_num);

	List<GiveAndTakePostVO> selectGATPostList(int me_num);

	List<MarketPostVO> selectMarketList(PostCriteria cri, int me_num);

}
