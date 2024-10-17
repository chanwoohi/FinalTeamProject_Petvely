package kr.kh.petvely.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.member.MemberDAO;
import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.MemberVO;

@Service
public class MemberDetailService implements UserDetailsService{

	@Autowired
	MemberDAO memberDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = memberDao.selectMember(username);

		return member == null ? null : new CustomUser(member);
	}

}
