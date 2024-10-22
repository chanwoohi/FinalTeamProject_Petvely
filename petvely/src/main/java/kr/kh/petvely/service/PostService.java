package kr.kh.petvely.service;

import java.util.List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.PostDAO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.RecommendVO;
import lombok.AllArgsConstructor;

@Service
public class PostService {
	@Autowired
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

	public boolean insertBookmark(int po_num, int bm_me_num) {
		
		return postDao.insertBookmark(po_num, bm_me_num);
		
	}

	public List<CommunityVO> selectCommunityList() {
		return postDao.selectCommunityList();
	}
	
	
	public boolean updatePost(PostVO post, int co_num) {
		if(post == null) {
			return false;
		}
		try {
			return postDao.updatePost(post, co_num);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//조회수 증가
	public void updateView(int po_num) {
		postDao.updateView(po_num);
		
	}
	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}

	// 커뮤니티 번호에 맞는 게시글 목록을 가져오는 메서드
	public List<PostVO> getAllPosts(int co_num) {
        return postDao.selectPostList(co_num);
    }		
	
	// 총 게시글 수를 가져오는 메서드 (페이징을 위해 사용)
	public int getPostCount(int co_num) {
        return postDao.selectCountPostList(co_num);
    }
	

	// 추천/비추천 처리 메서드
	public int insertRecommend(RecommendVO recommend) {
	    if (recommend == null) {
	        throw new RuntimeException("추천 정보가 null입니다.");
	    }
	
	    // 기존에 추천 여부 확인
	    RecommendVO dbRecommend = postDao.selectRecommend(recommend);
	
	    int po_num = recommend.getRe_po_num();  // 게시글 번호 가져오기
	
	    // 없으면 추가 후 추천 상태를 반환
	    if (dbRecommend == null) {
	        postDao.insertRecommend(recommend);  // 추천 추가
	    } else {
	        // 기존 상태와 새 상태가 같으면 추천을 취소
	        if (dbRecommend.getRe_state() == recommend.getRe_state()) {
	            postDao.deleteRecommend(dbRecommend.getRe_num());  // 추천 삭제
	            postDao.updateRecommendCount(po_num);  // 추천/비추천 수 갱신
	            return 0;  // 취소 상태 반환
	        }
	
	        // 기존 상태와 새 상태가 다르면 업데이트
	        postDao.deleteRecommend(dbRecommend.getRe_num());  // 기존 추천 삭제
	        postDao.insertRecommend(recommend);  // 새로운 추천 추가
	 }

	// 추천/비추천 수 업데이트
    postDao.updateRecommendCount(po_num);

    // 추천 상태 반환 (1: 추천, -1: 비추천)
    return recommend.getRe_state();
	}

	// 게시글 논리적 삭제 (po_delete 값을 1로 업데이트)
	public boolean logicalDeletePost(int po_num) {
	    return postDao.logicalDeletePost(po_num);  // 논리적 삭제
	}
	
	
	public boolean physicalDeletePost(int po_num) {
	    return postDao.deletePost(po_num);  // 실제 삭제
	}
}