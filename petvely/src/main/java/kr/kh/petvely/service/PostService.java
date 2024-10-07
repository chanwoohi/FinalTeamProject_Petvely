package kr.kh.petvely.service;

import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.PostDAO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.PostVO;
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
	public boolean deletePost(int po_num) {
		return postDao.deletePost(po_num);
	}
	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}
	public void markAsDeleted(int po_num) {
		Object postRepository;
		// 게시글을 삭제 상태로 업데이트
	    PostDAO post = postRepository.findById(po_num).orElseThrow(()
	     -> new PostNotFoundException(postNum));
	    post.setIsDeleted(true);  // 논리적으로 삭제된 상태로 변경
	    postRepository.save(post);
	}

}