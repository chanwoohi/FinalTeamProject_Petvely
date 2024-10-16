package kr.kh.petvely.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.PostDAO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.RecommendVO;
import kr.kh.petvely.pagination.PageMaker;
import kr.kh.petvely.pagination.PostCriteria;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
	
	private PostDAO postDao;
	
	public List<PostVO> getPostList(int co_num){
		return postDao.selectPostList(co_num);
		 // DAO를 통해 특정 커뮤니티 번호(co_num)에 해당하는 게시글을 가져옴
	}
	public boolean addPost(PostVO post) {
		if(post == null) {
			return false;
		}
		try {
			return postDao.insertPost(post);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public PostVO getPost(int po_num) {
		return postDao.selectPost(po_num);
	}
	
	public boolean updatePost(PostVO post) {
		if(post == null) {
			return false;
		}
		try {
			return postDao.updatePost(post);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//조회수 증가
	public void updateView(int po_num) {
		postDao.updateView(po_num);
		
	}
	// 게시글 논리적 삭제 (po_delete 값을 1로 업데이트)
	public boolean deletePost(int po_num) {
		// 실제 삭제가 아니라 po_delete를 1로 변경하여 논리적 삭제 처리
		return postDao.logicalDeletePost(po_num);  // PostDAO의 논리적 삭제 메서드 호출
	}
	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}
	// me_id와 함께 게시글 목록을 조회하는 메서드
	public List<PostVO> getPostpostListWithMemberId() {
		return postDao.selectPostWithMemberId();
	}
    // 추천/비추천 처리 메서드
	public int insertRecommend(RecommendVO recommend) {
	    if (recommend == null) {
	        throw new RuntimeException();
	    }

	    // 기존 추천 정보 확인
	    RecommendVO dbRecommend = postDao.selectRecommend(recommend);

	    // 기존 추천 정보가 없으면 새로 추가
	    if (dbRecommend == null) {
	        postDao.insertRecommend(recommend);
	        postDao.updateRecommendCount(recommend.getRe_po_num());  // 게시글 추천수 업데이트
	        return recommend.getRe_state();  // 추천/비추천 상태 반환
	    }

	    // 기존 추천 정보가 있으면 삭제
	    postDao.deleteRecommend(dbRecommend.getRe_num());

	    // 기존 상태와 동일하면 추천 취소
	    if (dbRecommend.getRe_state() == recommend.getRe_state()) {
	        postDao.updateRecommendCount(recommend.getRe_po_num());  // 게시글 추천수  업데이트
	        return 0;  // 취소 상태 반환
	    }

	    // 기존 상태와 다르면 상태 변경
	    postDao.insertRecommend(recommend);
	    postDao.updateRecommendCount(recommend.getRe_po_num());  // 게시글 추천수 업데이트
	    return recommend.getRe_state();  // 새로운 추천/비추천 상태 반환
	}
	public PageMaker getPageMaker(PostCriteria cri) {
		int count = postDao.selectCountPostList(cri);
		return new PageMaker(3, cri, count);
	}



}