package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.CommentVO;
import kr.kh.petvely.model.vo.GiveAndTakePostVO;
import kr.kh.petvely.model.vo.GiveAndTakeStateVO;
import kr.kh.petvely.model.vo.GiveAndTakeTypeVO;

public interface GATPostDAO {

	List<GiveAndTakePostVO> selectGATPostList();

	GiveAndTakePostVO selectGATPost(int po_num);

	boolean insertGATPost1(GiveAndTakePostVO GATPost);

	boolean insertGATPost2(GiveAndTakePostVO GATPost);
	
	boolean updateGATPost1(GiveAndTakePostVO GATPost);

	boolean updateGATPost2(GiveAndTakePostVO GATPost);

	boolean deleteGATPost1(int po_num);

	boolean deleteGATPost2(int po_num);

	List<GiveAndTakeStateVO> selectStateList();

	List<GiveAndTakeTypeVO> selectGATTypeList();

	void updateView(Integer po_num);

	boolean updategat_gat(GiveAndTakePostVO gATPost);

}
