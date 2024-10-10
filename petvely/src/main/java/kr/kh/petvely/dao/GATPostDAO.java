package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.GiveAndTakePostVO;

public interface GATPostDAO {

	List<GiveAndTakePostVO> selectGATPostList();

	GiveAndTakePostVO selectGATPost(int po_num);

	boolean insertGATPost1(GiveAndTakePostVO GATPost);

	boolean insertGATPost2(GiveAndTakePostVO GATPost);
	
	boolean updateGATPost1(GiveAndTakePostVO GATPost);

	boolean updateGATPost2(GiveAndTakePostVO GATPost);

	boolean deleteGATPost1(int po_num);

	boolean deleteGATPost2(int po_num);

}
