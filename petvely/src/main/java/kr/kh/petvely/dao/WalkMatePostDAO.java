package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.WalkMatePostVO;

public interface WalkMatePostDAO {

	List<WalkMatePostVO> selectWalkMatePostList();

	boolean insertWalkMatePost(WalkMatePostVO walkMatePost);

	WalkMatePostVO selectWalkMatePost(int po_num);

	boolean updateWalkMatePost(WalkMatePostVO walkMatePost);

}
