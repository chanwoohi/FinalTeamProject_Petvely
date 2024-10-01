package kr.kh.petvely.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.kh.petvely.service.PostService;

@Controller
public class PostController {
	
	    @Autowired
	    private PostService postService;

		@GetMapping("/post/list/0")
		public String postList() {
		    System.out.println("list");
		    return "/post/list";
	}
	@GetMapping("/post/lnsert/0")
	public String postInsert(@PathVariable int po_co_num) {
		return "post/insert";
	}
		
	

}
