package kr.kh.petvely.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.petvely.model.vo.WalkMateMemberVO;

public interface WalkMateDAO {

	void updateWmmApprove(@Param("aniNum")Integer aniNum, @Param("po_num")int po_num);

	void deleteWalkMate(@Param("me_num")int me_num, @Param("po_num")int po_num);


}
