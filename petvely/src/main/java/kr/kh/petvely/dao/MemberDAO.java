package kr.kh.petvely.dao;

import kr.kh.petvely.model.vo.MemberVO;

public interface MemberDAO {

	MemberVO selectMember(String username);

}
