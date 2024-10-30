package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.AnimalVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.service.AnimalService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MypageController {
	
	@Autowired
	private AnimalService animalService;
	
	@GetMapping("/mypage/mypage")
	public String showProfilePage(Model model) {
		model.addAttribute("currentPage", "user");
		return "/mypage/mypage";
	}
	@GetMapping("/mypage/pet")
	public String showgetProfilePage(Model model, @AuthenticationPrincipal CustomUser customUser) {
		model.addAttribute("currentPage", "pet");
		

		if(customUser != null) {
			MemberVO user = customUser.getMember();
			List<AnimalVO> petList = animalService.selectPetList(user.getMe_num());
			
			model.addAttribute("petList", petList);
		}
		
		return "/mypage/pet";
	}

}
