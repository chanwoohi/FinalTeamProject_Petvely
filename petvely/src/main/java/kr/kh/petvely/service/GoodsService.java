package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.MarketPostDAO;
import kr.kh.petvely.model.vo.GoodsTypeVO;


@Service
public class GoodsService {

	@Autowired
	MarketPostDAO marketPostDao;

	public List<GoodsTypeVO> getTypes() {
		
		return marketPostDao.selectTypes();
	}
	
	
}
