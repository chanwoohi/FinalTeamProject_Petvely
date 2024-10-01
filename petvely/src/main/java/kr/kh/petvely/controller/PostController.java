package kr.kh.petvely.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.service.PostService;

@Controller
public class PostController {
	
	    @Autowired
	    private PostService postService;

	@GetMapping("/post/list/0")
	public String postList() {
	    return "/post/list";
	}
	@GetMapping("/post/lnsert")
	public String postInsert() {
		System.out.println("왜ㅡㅡ");
		return "post/insert";
	}
	@PostMapping("/post/lnsert")
	public String postInsertPost(PostVO post) {
		boolean res = postService.addPost(post);
		if(res) {
			return "redirect:/post/list/";
		}
		return "redirect:/post/list/";
		
	}


		
	

}
