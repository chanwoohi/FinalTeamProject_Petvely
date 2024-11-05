package kr.kh.petvely.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.member.MemberDAO;
import kr.kh.petvely.model.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	public boolean checkRedundancy(String type, String value) {
		boolean result = false;
		try {
			if (type.equals("phone")) {
				value = value.replace("-", "").replace(".", "").replaceAll("\\s", "").strip();
			}
			result = memberDao.checkRedundancy(type, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean signup(MemberVO memberVO) {
		if (memberVO == null)
			return false;
		
		String encPw = passwordEncoder.encode(memberVO.getMe_pw());
		memberVO.setMe_pw(encPw);
		
		memberVO.setMe_phone(memberVO.getMe_phone().replace("-", "").replace(".", "").replaceAll("\\s", "").strip());
		memberVO.setMe_ms_status("활동");
			
		return memberDao.insertMember(memberVO);
	}

	/*
	public MemberVO login(MemberVO memberVO) {
		if (memberVO == null)
			return null;
		
		MemberVO selectedVO = memberDao.selectMember(memberVO.getMe_id());
		log.info("1 : " + selectedVO);
		
		if (selectedVO == null)
			return null;
		
		if (selectedVO.getMe_pw().equals(memberVO.getMe_pw()) == false) {
			return null;
		}

		log.info("2 : " + selectedVO);
		return selectedVO;
	}
	*/
	
	public MemberVO login(MemberVO member) {
		if(member == null) {
			return null;
		}
		//다오에게 아이디를 주면서 아이디와 일치하는 회원 정보를 가져오라고 시킴
		MemberVO user = memberDao.selectMember(member.getMe_id());
		//가져온 회원 정보가 null이면 null을 리턴
		if(user == null) {
			return null;
		}
		//비번과 가져온 회원정보의 비번이 같으면 가져온 회원 정보를 리턴
		if(user.getMe_pw().equals(member.getMe_pw())) {
			return user;
		}
		//다르면 null을 리턴 
		return null;
	}

	/*
	 * private boolean regexCheckMember(MemberVO member) { if(member == null ||
	 * member.getMe_pw() == null || member.getMe_id() == null) return false;
	 * if(!Pattern.matches("^\\w{6,13}$", member.getMe_id())) return false;
	 * if(!Pattern.matches("^[a-zA-Z0-9!@#$]{6,15}$", member.getMe_pw())) return
	 * false; return true; }
	 */

	public MemberVO getMeId(int po_me_num) {
		
		return memberDao.selectMeId(po_me_num);
	}

	
}
	