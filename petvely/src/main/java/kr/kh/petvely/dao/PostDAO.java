package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.MarketPostVO;

public interface PostDAO {

	List<MarketPostVO> selectMarketList();

	


}
