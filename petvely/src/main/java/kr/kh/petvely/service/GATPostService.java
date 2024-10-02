package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.GATPostDAO;
import kr.kh.petvely.model.vo.GiveAndTakePostVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GATPostService {
	
	@Autowired
	private GATPostDAO postDao;
	
	public List<GiveAndTakePostVO> getGATPostList() {
		return postDao.selectGATPostList();
	}

	public GiveAndTakePostVO getGATPost(int po_num) {
		return postDao.selectGATPost(po_num);
	}

	public boolean addGATPost(GiveAndTakePostVO GiveAndTakePost) {
		if(GiveAndTakePost == null) {
			return false;
		}
		try {
			return postDao.insertGATPost(GiveAndTakePost);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
