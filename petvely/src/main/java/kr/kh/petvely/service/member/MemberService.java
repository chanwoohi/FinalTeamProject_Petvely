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

	public boolean signup(MemberVO memberVo) {
		if (memberVo == null)
			return false;
		
		String encPw = passwordEncoder.encode(memberVo.getMe_pw());
		memberVo.setMe_pw(encPw);
		
		memberVo.setMe_phone(memberVo.getMe_phone().replace("-", "").replace(".", "").replaceAll("\\s", "").strip());
		memberVo.setMe_ms_status("활동");
			
		return memberDao.insertMember(memberVo);
	}

	public MemberVO getMeId(int po_me_num) {
		
		return memberDao.selectMeId(po_me_num);
	}

	public boolean updateMember(MemberVO memberVo) {
		if (memberVo == null)
			return false;

		String encPw = passwordEncoder.encode(memberVo.getMe_pw());
		memberVo.setMe_pw(encPw);
		
		memberVo.setMe_phone(memberVo.getMe_phone().replace("-", "").replace(".", "").replaceAll("\\s", "").strip());
		
		return memberDao.updateMember(memberVo);
	}
}
	