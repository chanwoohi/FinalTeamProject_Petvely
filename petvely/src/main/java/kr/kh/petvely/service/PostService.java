package kr.kh.petvely.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.PostDAO;

@Service
public class PostService {
	@Autowired
	private PostDAO postDao;

	public boolean deletePost(int po_num) {
		return postDao.deletePost(po_num);
	}
	
}
