package kr.kh.petvely.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import kr.kh.petvely.service.MemberService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class Membercontroller {
	
	private MemberService memberService;

	@GetMapping("/member/login")
	public String MemberLogin(Model model) {
		return "member/login";
	}
	
	@GetMapping("/member/signup")
	public String MemberSignup() {
		return "member/signup";
	}
	
	@GetMapping("/member/mypage")
	public String MemberMypage() {
		return "member/mypage";
	}
	
	
}