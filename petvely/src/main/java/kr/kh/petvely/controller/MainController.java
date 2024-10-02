package kr.kh.petvely.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.kh.petvely.dao.MarketPostDAO;

@Controller
public class MainController {
	
	@Autowired
	MarketPostDAO postDao;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("name", "홍길동");

		return "home";
	}
	
}