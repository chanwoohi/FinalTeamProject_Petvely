package kr.kh.petvely.controller;


import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.CommentVO;
import kr.kh.petvely.model.vo.MemberVO;
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
		return "comment/list";
	}

	@PostMapping("/comment/list")
	@ResponseBody
	public List<CommentVO> ComentListPost(Model model, @RequestBody int po_num) {
		List<CommentVO> list = commentService.getCommentList(po_num);
		model.addAttribute("list",list);
		return list;
		
	}
	@PostMapping("/comment/insert")
	@ResponseBody
	public boolean insertPost(@RequestBody CommentVO comment, @AuthenticationPrincipal CustomUser customUser) {
		MemberVO user = customUser.getMember();
		System.out.println(comment);
		return commentService.insertComment(comment, user);
	}
	
	@PostMapping("/comment/insert1")
	@ResponseBody
	public boolean insertPost1(@RequestBody CommentVO comment, @AuthenticationPrincipal CustomUser customUser) {
		MemberVO user = customUser.getMember();
		System.out.println(comment);
		return commentService.insertComment1(comment, user);
	}
	
	@PostMapping("/comment/insert2")
	@ResponseBody
	public boolean insertPost2(@RequestBody CommentVO comment, @AuthenticationPrincipal CustomUser customUser) {
		MemberVO user = customUser.getMember();
		System.out.println(comment);
		return commentService.insertComment2(comment, user);
	}
	
	@PostMapping("/comment/delete")
	@ResponseBody
	public boolean deleteComment(@RequestBody CommentVO comment) {
		System.out.println(comment);
		return commentService.deleteComment(comment);
	}
	
	@GetMapping("/comment/update")
	public String updateComment(Model model, int cm_num) {
		CommentVO comment = commentService.selectComment(cm_num);
		System.out.println(comment);
		model.addAttribute("comment", comment);
		return "comment/update";
	}
	
	@PostMapping("/comment/update")
	public String updateComment(CommentVO comment) {
		boolean res =commentService.updateComment(comment);
		System.out.println(comment);
		if(res) {
			return "comment/close";
		}
		return "redirect:/comment/update/"+comment.getCm_num();
	}
	
	
}