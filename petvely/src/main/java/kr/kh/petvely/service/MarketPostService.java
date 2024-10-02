package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import kr.kh.petvely.dao.MarketPostDAO;
import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.PostVO;



@Service
public class MarketPostService {

	@Autowired
	MarketPostDAO marketPostDao;
	
	public List<MarketPostVO> getMarketList() {
		
		return marketPostDao.selectMarketList();
	}

	public PostVO getMarketPost(int po_num) {
		return marketPostDao.selectPost(po_num);
	}

	public boolean addPost(MarketPostVO marketPost) {
		if(marketPost == null) {
			return false;
		}try {
			marketPostDao.insertPost(marketPost);
			marketPostDao.insertMarketPost(marketPost);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}



	

}
