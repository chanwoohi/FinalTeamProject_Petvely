package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.PostDAO;
import kr.kh.petvely.model.vo.MarketPostVO;



@Service
public class PostService {

	@Autowired
	PostDAO postDao;
	
	public List<MarketPostVO> getMarketList() {
		
		return postDao.selectMarketList();
	}


	

}
