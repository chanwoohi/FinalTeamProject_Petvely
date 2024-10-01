package kr.kh.petvely.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.PostDAO;
import kr.kh.petvely.dao.WalkMatePostDAO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.WalkMatePostVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
	
	private PostDAO postDao;
	
	public List<PostVO> getPostList(){
		return postDao.selectPostList();
	}

}
