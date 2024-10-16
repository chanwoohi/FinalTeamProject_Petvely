package kr.kh.petvely.service;

import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.MemberDAO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {
	
	private MemberDAO memberDao;

}
