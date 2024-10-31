package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.FileVO;
import kr.kh.petvely.model.vo.GoodsTypeVO;
import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.pagination.PostCriteria;

public interface MarketPostDAO {

	List<MarketPostVO> selectMarketList();

	MarketPostVO selectPost(int po_num);

	boolean insertMarketPost(MarketPostVO marketPost);

	boolean insertPost(PostVO post);

	List<GoodsTypeVO> selectTypes();

	boolean updateTradeState(int po_num, String state);

	void insertFile(FileVO fileVo);

	List<FileVO> selectFileList(int po_num);

	boolean updateMarketPost(MarketPostVO marketPost);

	FileVO selectFile(int fi_num, MarketPostVO marketPost);

	boolean deleteFile(FileVO file);

	FileVO selectFileByPo_num(int po_num);

	



	








	


}
