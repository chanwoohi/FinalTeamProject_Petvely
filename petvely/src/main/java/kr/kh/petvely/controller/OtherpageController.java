package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.kh.petvely.model.vo.AnimalVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.service.AnimalService;
import kr.kh.petvely.service.member.MemberService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class OtherpageController {
	
	@Autowired
	private AnimalService animalService;
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/mypage/otherpet/{me_num}")
	public String showgetProfilePage(Model model,@PathVariable int me_num) {
		
		MemberVO otherUser = memberService.getMeId(me_num);
		String me_id = otherUser.getMe_id();
		model.addAttribute("me_id",me_id);
		
		List<AnimalVO> otherPetList = animalService.selectOtherPetList(me_num);
		model.addAttribute("otherPetList",otherPetList);
		System.out.println(otherPetList);
		return "/mypage/otherpet";
		
	}
	
}
