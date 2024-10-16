package kr.kh.petvely.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.kh.petvely.model.vo.CommentVO;
import kr.kh.petvely.service.CommentService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CommentController {
	
	private CommentService commentService;
	
	@GetMapping("/comment/list")
	public String CommentList(Model model) {
		List<CommentVO> list = commentService.getCommentList();
		model.addAttribute("list", list);
		System.out.println(list);
		return "comment/list";
	}

	
}