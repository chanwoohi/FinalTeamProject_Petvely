package kr.kh.petvely.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.PostDAO;
import kr.kh.petvely.model.vo.CommunityVO;

@Service
public class PostService {
	@Autowired
	private PostDAO postDao;

	public boolean deletePost(int po_num) {
		return postDao.deletePost(po_num);
	}

	public boolean insertBookmark(int po_num, int bm_me_num) {
		
		return postDao.insertBookmark(po_num, bm_me_num);
		
	}

	public List<CommunityVO> selectCommunityList() {
		return postDao.selectCommunityList();
	}
	
	
}
