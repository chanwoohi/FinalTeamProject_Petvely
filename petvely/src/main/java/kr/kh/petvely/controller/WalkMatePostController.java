package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.WalkMatePostVO;
import kr.kh.petvely.service.WalkMatePostService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WalkMatePostController {
	
	private WalkMatePostService walkMatePostService;
	
	@GetMapping("/walkmatepost/list")
	public String walkmatepostList(Model model) {
		List<WalkMatePostVO> list = walkMatePostService.getWalkMatePostList();
		model.addAttribute("list", list);
		return "/walkmatepost/list";
	}
	
	@GetMapping("/walkmatepost/insert")
	public String walkmatepostInsert() {
		return "/walkmatepost/insert";
	}
	
	@PostMapping("/walkmatepost/insert")
	public String walkmatepostInsertPost(PostVO post, WalkMatePostVO walkMatePost) {
		if(walkMatePostService.insertWalkMatePost(post, walkMatePost)) {
			return "redirect:/walkmatepost/list";
		}
		return "redirect:/walkmatepost/insert";
	}
	
}
