package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.GiveAndTakePostVO;

public interface PostDAO {

	int count();

	List<GiveAndTakePostVO> selectGATPostList();

	GiveAndTakePostVO selectGATPost(int po_num);

	boolean insertGATPost(GiveAndTakePostVO GiveAndTakePost);

}
