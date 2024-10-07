package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.service.PostService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PostController {
	
	@Autowired
	private PostService postService;

	@GetMapping("/post/list/{co_num}")
	public String postList(Model model) {
		List<PostVO> list = postService.getPostList();
		//게시글 목록을 가져와서 화면에 전달
		List<CommunityVO> communities = postService.getCommunityList();
		model.addAttribute("list", list);
		model.addAttribute("communities", communities);
	    return "/post/list";
	}
	@GetMapping("/post/insert") //글가져오기
	public String postInsert() {
		return "post/insert";
	}
	@PostMapping("/post/insert/{co_num}") //글쓰기
	public String postInsertPost(PostVO post) {

		boolean res = postService.addPost(post);
		System.out.println(res);
		return "post/insert";
	}
	@GetMapping("/post/detail/{po_num}") //게시글 상세
	public String postDetail(Model model, @PathVariable int po_num) {
		System.out.println(po_num);
		PostVO post = postService.getPost(po_num);
		model.addAttribute("post", post);
		System.out.println(post);
		return "post/detail";	
	}
	@GetMapping("/post/update/{po_num}") //게시글 수정 가져오기
	public String postUpdate(Model model, @PathVariable int po_num) {
		PostVO post = postService.getPost(po_num);
		System.out.println(po_num);
		model.addAttribute("post", post);
		return "post/update";
	}
	@PostMapping("/post/update/{po_num}") //게시글 쓴거 저장
	public String postUpdatePost(Model model, @PathVariable int po_num, PostVO post) {
		post.setPo_num(po_num);
		boolean res = postService.updatePost(post);
		if(res) {
			return "redirect:/post/detail/"+po_num;
		}
		return "redirect:/post/update/"+po_num;
	}
	@GetMapping("/post/delete/{po_num}")
	public String postDelete(Model model, @PathVariable int po_num) {
		boolean res = postService.deletePost(po_num);
		if(res) {
			return "redirect:/post/list/"+po_num;
		}
		return "redirect:/post/detail/"+po_num;
	
	}
	

}
