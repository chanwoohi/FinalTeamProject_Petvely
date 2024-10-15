package kr.kh.petvely.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.kh.petvely.service.PostService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PostController {
	@Autowired
	private PostService postService;
	
	@GetMapping("/post/bookmark/{po_num}")
	private String postBookmark(Model model,
								@PathVariable int po_num) {
		// 나중엔 로그인 된 사용자의 me_num 그대로 가져오게 해야 함
		int bm_me_num = 3;
		
		if(postService.insertBookmark(po_num, bm_me_num)) {
			System.out.println("즐겨찾기 등록 성공!");
		} else {
			System.out.println("즐겨찾기 등록 실패!");
		}
		return "/home";
	}
	
}
