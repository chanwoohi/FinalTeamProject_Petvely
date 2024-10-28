package kr.kh.petvely.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.service.member.MemberService;
import kr.kh.petvely.utils.NoName;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/")
public class MemberController {
	private final String viewRoute = "view/member/";
	private final NoName util = NoName.getInstance();
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("signup")
	public String memberSignup(Model model) {
		log.info(util.getCurrentMethodName());
		return viewRoute + "signup";
	}
	
	@ResponseBody
	@PostMapping("check/value")
	public boolean memberCheckValue_Post(String type, String value) {
		log.info(util.getCurrentMethodName() + " : " + type + " : " + value);
		boolean result = memberService.checkRedundancy(type, value);
		log.info("" + result);
		return result;
	}
	
	@PostMapping("signup")
	public String memberSignup_Post(MemberVO memberVO) {
		log.info(util.getCurrentMethodName() + " : " + memberVO);
		boolean result = memberService.signup(memberVO);
		log.info("" + result);
		
		return "redirect:/";
	}
	
	@GetMapping("login")
	public String memberLogin() {
		log.info(util.getCurrentMethodName());
		return viewRoute + "login";
	}
	
	
	@PostMapping("login") 
	public String memberLogin_post(Model model, MemberVO memberVO, HttpSession session) {
		log.info(util.getCurrentMethodName() + " : " + memberVO); 
		MemberVO user = memberService.login(memberVO);
		System.out.println(user);
		model.addAttribute("user", user); 
		log.info("memberLogin_pos123123t : " + user); 
		return "redirect:/"; 
	}
		 
	@GetMapping("logout") 
	public String memberLogout() {
	log.info(util.getCurrentMethodName());
		return viewRoute + "logout";
	}
	 
	@GetMapping("mypage")
	public String memberMypage() {
		log.info(util.getCurrentMethodName());
		return "member/mypage";
	}
	
}