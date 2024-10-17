package kr.kh.petvely.dao.member;

import kr.kh.petvely.model.vo.MemberVO;

public interface MemberDAO {

	boolean checkRedundancy(String type, String value);

	boolean insertMember(MemberVO memberVO);
	
	MemberVO selectMember(String me_id);

}
