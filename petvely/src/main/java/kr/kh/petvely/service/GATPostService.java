package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.GATPostDAO;
import kr.kh.petvely.model.vo.GiveAndTakePostVO;
import kr.kh.petvely.model.vo.GiveAndTakeStateVO;
import kr.kh.petvely.model.vo.GiveAndTakeTypeVO;
import kr.kh.petvely.model.vo.GoodsTradeStateVO;
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
	
	public void updatePostView(Integer po_num) {
		gatpostDao.updateView(po_num);
	}

	public boolean addGATPost1(GiveAndTakePostVO GATPost) {
		if(GATPost == null) {
			return false;
		}
		try {
			return gatpostDao.insertGATPost1(GATPost);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean addGATPost2(GiveAndTakePostVO GATPost) {
		if(GATPost == null) {
			return false;
		}
		try {
			return gatpostDao.insertGATPost2(GATPost);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateGATPost1(GiveAndTakePostVO GATPost) {
		if(GATPost == null) {
			return false;
		}
		try {
			return gatpostDao.updateGATPost1(GATPost);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateGATPost2(GiveAndTakePostVO GATPost) {
		if(GATPost == null) {
			return false;
		}
		try {
			return gatpostDao.updateGATPost2(GATPost);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteGATPost1(int po_num) {
		return gatpostDao.deleteGATPost1(po_num);
	}

	public boolean deleteGATPost2(int po_num) {
		return gatpostDao.deleteGATPost2(po_num);
	}

	public List<GiveAndTakeStateVO> gatStateList() {
		return gatpostDao.selectStateList();
	}

	public List<GiveAndTakeTypeVO> gatTypeList() {
		return gatpostDao.selectGATTypeList();
	}

	
	

}
