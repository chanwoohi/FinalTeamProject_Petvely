package kr.kh.petvely.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.petvely.model.vo.PostUserSelectedPetsVO;
import kr.kh.petvely.model.vo.WalkMateMemberVO;
import kr.kh.petvely.model.vo.WalkMatePostVO;

public interface WalkMatePostDAO {

	List<WalkMatePostVO> selectWalkMatePostList();

	boolean insertWalkMatePost(WalkMatePostVO walkMatePost);

	WalkMatePostVO selectWalkMatePost(int po_num);

	boolean updateWalkMatePost(WalkMatePostVO walkMatePost);

	void insertWalkMateMember(@Param("pet")PostUserSelectedPetsVO pet);

	List<WalkMateMemberVO> selectWalkMateMember(int po_num);

	void deleteWalkMateMember(int po_num);

	boolean updateWalkMatePostState(int po_num);

}
