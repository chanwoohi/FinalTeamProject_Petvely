package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.WalkMatePostVO;
import kr.kh.petvely.service.PostService;
import kr.kh.petvely.service.WalkMatePostService;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class WalkMatePostController {
	
	@Autowired
	private WalkMatePostService walkMatePostService;
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/walkmatepost/list")
	public String walkmatepostList(Model model) {
		List<WalkMatePostVO> list = walkMatePostService.getWalkMatePostList();
		model.addAttribute("list", list);
		return "/walkmatepost/list";
	}
	
	@GetMapping("/walkmatepost/insert")
	public String walkmatepostInsert() {
		return "/walkmatepost/insert";
	}
	
	@PostMapping("/walkmatepost/insert")
	public String walkmatepostInsertPost(PostVO post, WalkMatePostVO walkMatePost) {
		if(walkMatePostService.insertWalkMatePost(post, walkMatePost)) {
			return "redirect:/walkmatepost/list";
		}
		return "redirect:/walkmatepost/insert";
	}
	
	@GetMapping("/walkmatepost/detail/{po_num}")
	public String walkmatepostDetail(Model model, @PathVariable int po_num) {
		WalkMatePostVO walkMatePost = walkMatePostService.getWalkMatePost(po_num);
		model.addAttribute("walkMatePost", walkMatePost);
		return "/walkmatepost/detail";
	}
	
	@GetMapping("/walkmatepost/update/{po_num}")
	public String walkmatepostUpdate(Model model, @PathVariable int po_num) {
		WalkMatePostVO walkMatePost = walkMatePostService.getWalkMatePost(po_num);
		model.addAttribute("walkMatePost", walkMatePost);
		return "/walkmatepost/update";
	}
	
	@PostMapping("/walkmatepost/update/{po_num}")
	public String walkmatepostUpdatePost(Model model, @PathVariable int po_num, WalkMatePostVO walkMatePost) {
		if(walkMatePostService.updateWalkMatePost(walkMatePost)) {
			System.out.println(walkMatePost);
			return "redirect:/walkmatepost/list";
		}
		return "redirect:/walkmatepost/detail/"+po_num;
	}
	
	@GetMapping("/walkmatepost/delete/{po_num}")
	public String walkmatepostDelete(Model model, @PathVariable int po_num) {
		/* 
		 * postService에 맡긴 이유는 삭제 했을 때 DB에서 CASCADE 설정하면 어차피 같이 지워짐 ( po_num 공유라 상관 없나?)
		 * 상관 있는데 mysql 자체에서 설정해서 같이 삭제 시키게 했음
		 * 작동하면 다른 게시판에서 쓸 수 있으니까 postService로 보냄	
		*/ 
		
		if(postService.deletePost(po_num)) {
			return "redirect:/walkmatepost/list";
		}
		return "redirect:/walkmatepost/detail/"+po_num;
	}
	
	@GetMapping("/walkmatepost/enroll")
	public String walkmatepostEnroll() {
		/*
		 * if("해당 작성자가 펫 등록이 되어있는지 물어보는 코드") { 
		 * return "redirect:/walkmatepost/enroll"; 
		 * }
		 * return "redirect:/펫 등록 하는 코드 ";
		 * 이런 식으로 바꿀 것임 !
		*/
		return "/walkmatepost/enroll";
	}
	
}
