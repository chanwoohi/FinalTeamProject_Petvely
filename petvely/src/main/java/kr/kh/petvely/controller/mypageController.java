package kr.kh.petvely.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@AllArgsConstructor
public class mypageController {
	@GetMapping("/mypage/mypage")
	public String showProfilePage(Model model) {
		model.addAttribute("currentPage", "user");
		return "/mypage/mypage";
	}
	@GetMapping("/mypage/pet")
	public String showgetProfilePage(Model model) {
		model.addAttribute("currentPage", "pet");
		return "/mypage/pet";
	}
	
	
}
