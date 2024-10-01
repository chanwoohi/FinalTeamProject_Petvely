package kr.kh.petvely.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.kh.petvely.dao.PostDAO;
import kr.kh.petvely.model.vo.PostVO;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String home(Model model) {
		return "home";
	}
	
}