package kr.kh.petvely.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.PostDAO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.RecommendVO;
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
	        throw new RuntimeException();
	    }
		return 0;

	}

}