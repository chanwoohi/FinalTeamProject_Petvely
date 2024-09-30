package kr.kh.petvely.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WalkMatePostController {
	
	@GetMapping("/walkmatepost/list")
	public String walkmatepostList() {
		
		return "/walkmatepost/list";
	}
	
	
}
