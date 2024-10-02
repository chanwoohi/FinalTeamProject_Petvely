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
	private GATPostDAO gatpostDao;
	
	public List<GiveAndTakePostVO> getGATPostList() {
		return gatpostDao.selectGATPostList();
	}

	public GiveAndTakePostVO getGATPost(int po_num) {
		return gatpostDao.selectGATPost(po_num);
	}

	public boolean addGATPost(GiveAndTakePostVO GATPost) {
		if(GATPost == null) {
			return false;
		}
		try {
			return gatpostDao.insertGATPost(GATPost);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateGATPost(GiveAndTakePostVO GATPost) {
		if(GATPost == null) {
			return false;
		}
		try {
			return gatpostDao.updateGATPost(GATPost);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteGATPost(int po_num) {
		return gatpostDao.deleteGATPost(po_num);
	}
	
}
