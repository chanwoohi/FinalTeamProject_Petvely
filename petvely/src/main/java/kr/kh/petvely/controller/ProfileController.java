package kr.kh.petvely.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.MemberVO;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProfileController{
 
	@GetMapping("/profile/profile")
	public String profileProfile(Model model, @AuthenticationPrincipal CustomUser customUser, int me_num) {
		MemberVO user = customUser.getMember();
		model.addAttribute("user", user);
		System.out.println(user);
		System.out.println(me_num);
		return "profile/profile";
	}
	
	@GetMapping("/profile/message")
	public String profileMessage(Model model, @AuthenticationPrincipal CustomUser customUser, int me_num) {
		MemberVO user = customUser.getMember();
		model.addAttribute("user", user);
		System.out.println(user);
		System.out.println(me_num);
		return "profile/message";
	}
}
