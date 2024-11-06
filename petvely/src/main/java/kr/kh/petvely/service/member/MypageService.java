package kr.kh.petvely.service.member;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.member.MypageDAO;
import kr.kh.petvely.model.vo.CommentVO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.GiveAndTakePostVO;
import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.WalkMatePostVO;
import kr.kh.petvely.pagination.PostCriteria;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MypageService {

	private MypageDAO mypageDao;
	
	public List<CommentVO> getCommentList(int me_num) {
		return mypageDao.selectCommentList(me_num);
	}

	public List<CommunityVO> getCommunityList() {
		return mypageDao.selectCommunityList();
	}

	public List<PostVO> getPostList(int co_num, int me_num) {
		return mypageDao.selectPostList(co_num, me_num);
	}

	public List<WalkMatePostVO> getWalkMatePostList(int me_num) {
		return mypageDao.selectWalkMatePostList(me_num);
	}

	public List<GiveAndTakePostVO> getGATPostList(int me_num) {
		return mypageDao.selectGATPostList(me_num);
	}

	public List<MarketPostVO> getMarketList(PostCriteria cri, int me_num) {
		return mypageDao.selectMarketList(cri, me_num);
	}

}
