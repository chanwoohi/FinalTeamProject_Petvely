package kr.kh.petvely.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.member.MemberDAO;
import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.user.UserRole;
import kr.kh.petvely.model.vo.MemberVO;

@Service
public class MemberDetailService implements UserDetailsService{

	@Autowired
	MemberDAO memberDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = memberDao.selectMember(username);
		System.out.println("로그인된 사용자 정보: " + member.getMe_id());  // 디버깅 로그
		
		if (member.getMe_authority().equals(UserRole.STOP.name())) {
			System.out.println("error");
			throw new AuthenticationCredentialsNotFoundException("계정이 정지되었습니다. 관리자에게 문의하세요.");
		}

		return member == null ? null : new CustomUser(member);
	}

}
