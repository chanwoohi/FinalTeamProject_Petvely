package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.GoodsTypeVO;
import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.PostVO;

public interface MarketPostDAO {

	List<MarketPostVO> selectMarketList();

	PostVO selectPost(int po_num);

	boolean insertMarketPost(MarketPostVO marketPost);

	boolean insertPost(MarketPostVO marketPost);

	List<GoodsTypeVO> selectTypes();

	boolean updateTradeState(int po_num, String state);


	


}
