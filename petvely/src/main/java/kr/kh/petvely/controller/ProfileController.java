package kr.kh.petvely.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ProfileController{
 
	@GetMapping("/profile/profile")
	public String updateComment(Model model) {
		return "profile/profile";
	}
	
}
