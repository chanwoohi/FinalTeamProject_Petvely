package kr.kh.petvely.service;


import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.PostDAO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
	
	private PostDAO postDao;

	public boolean deletePost(int po_num) {
		return postDao.deletePost(po_num);
	}
	
}
