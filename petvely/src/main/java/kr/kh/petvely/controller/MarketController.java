package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.service.PostService;

@Controller
public class MarketController {

	@Autowired
	PostService postService;
	
	@GetMapping("/post/market")
	public String postList(Model model) {
	
		List<MarketPostVO> list = postService.getMarketList();

		model.addAttribute("list",list);
		System.out.println();
		return "post/market";
	}
}
