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
	// 시큐리티 내가 추가하려고 했을 때 만든 애인데 얘 떄문에 오류 발생?? 잠시 지우자
	@Autowired
	MemberDAO memberDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = memberDao.selectMember(username);

		 System.out.println("로그인된 사용자 정보: " + member.getMe_id());  // 디버깅 로그

		return member == null ? null : new CustomUser(member);
	}

}
