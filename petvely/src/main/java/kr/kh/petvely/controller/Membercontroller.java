package kr.kh.petvely.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class Membercontroller {

	@GetMapping("/member/login")
	public String MemberLogin() {
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