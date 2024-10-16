package kr.kh.petvely.dao;

import kr.kh.petvely.model.vo.MemberVO;

public interface MemberDAO {

	boolean selectMember123(MemberVO member);

	MemberVO selectMember(String username);

}
