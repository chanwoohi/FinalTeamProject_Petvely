package kr.kh.petvely.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class mypageController {
	@GetMapping("/mypage/mypage")
	public String showProfilePage(Model model) {
		return "/mypage/mypage";
	}
	
}
