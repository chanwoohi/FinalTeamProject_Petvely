package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.CommentVO;
import kr.kh.petvely.model.vo.GiveAndTakePostVO;
import kr.kh.petvely.model.vo.GiveAndTakeStateVO;
import kr.kh.petvely.model.vo.GiveAndTakeTypeVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.model.vo.Sido_AreasVO;
import kr.kh.petvely.service.AddressService;
import kr.kh.petvely.service.GATPostService;
import kr.kh.petvely.service.PostService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class GATPostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private GATPostService gatPostService;
	private AddressService addressService;
	
	@GetMapping("/gatpost/list")
	public String GTAPostlist(Model model) {
		List<GiveAndTakePostVO> list = gatPostService.getGATPostList();
		model.addAttribute("list", list);
		return "gatpost/list";
	}
	
	@GetMapping("/gatpost/detail/{po_num}")
	public String postDetail(Model model, 
							@PathVariable int po_num,
							@AuthenticationPrincipal CustomUser customUser) {
		MemberVO user = customUser.getMember();
		int bm_me_num = user.getMe_num();
		
		System.out.println("gatpost가 받는 po_num : " + po_num);
		Integer bookmark = postService.selectBookmark(bm_me_num, po_num);
		if (bookmark != null) {
			System.out.println("bookmark : " + bookmark);
			model.addAttribute("bookmark", bookmark);
		}
		
		model.addAttribute("po_num", po_num);
		
		gatPostService.updatePostView(po_num);
		GiveAndTakePostVO GATPost = gatPostService.getGATPost(po_num);
		System.out.println(GATPost);
		model.addAttribute("GATPost", GATPost);
		return "gatpost/detail";
	}
	
	@GetMapping("/gatpost/insert")
	public String GATPostInsert(Model model) {
		List<Sido_AreasVO> sidoList = addressService.getSidoList();
		model.addAttribute("sidoList", sidoList);
		List<GiveAndTakeStateVO> gatstateList = gatPostService.gatStateList();
		model.addAttribute("gatstateList", gatstateList);
		List<GiveAndTakeTypeVO> gatTypeList = gatPostService.gatTypeList();
		model.addAttribute("gatTypeList", gatTypeList);
		return "gatpost/insert";
	}
	
	
	@PostMapping("/gatpost/insert")
	public String GTAPostInsertPost(GiveAndTakePostVO GATPost) {
		boolean res = gatPostService.addGATPost1(GATPost);
		boolean res2 = gatPostService.addGATPost2(GATPost);
		if(res || res2) {
			return "redirect:/gatpost/list";
		}
		return "redirect:/gatpost/insert";
	}
	
	
	@GetMapping("/gatpost/update/{po_num}")
	public String postUpdate(Model model, @PathVariable int po_num) {
		List<Sido_AreasVO> sidoList = addressService.getSidoList();
		model.addAttribute("sidoList", sidoList);
		List<GiveAndTakeStateVO> gatstateList = gatPostService.gatStateList();
		model.addAttribute("gatstateList", gatstateList);
		GiveAndTakePostVO GATPost = gatPostService.getGATPost(po_num);
		model.addAttribute("GATPost", GATPost);
		List<GiveAndTakeTypeVO> gatTypeList = gatPostService.gatTypeList();
		model.addAttribute("gatTypeList", gatTypeList);
		return "gatpost/update";
	}
	
	@PostMapping("/gatpost/update/{po_num}")
	public String postUpdatePost(Model model, @PathVariable int po_num, GiveAndTakePostVO GATPost) {
		GATPost.setPo_num(po_num);
		boolean res = gatPostService.updateGATPost1(GATPost);
		boolean res2 = gatPostService.updateGATPost2(GATPost);
		if(res || res2) {
			return "redirect:/gatpost/list";
		}
		return "redirect:/gatpost/update";
	}
	
	@GetMapping("/gatpost/delete/{po_num}")
	public String postDelete(@PathVariable int po_num) {
		boolean res = gatPostService.deleteGATPost1(po_num);
		boolean res2 = gatPostService.deleteGATPost2(po_num);
		if(res || res2) {
			return "redirect:/gatpost/list";
		}
		return "redirect:/gatpost/detail/" + po_num;
	}
	
	@PostMapping("/comment/list")
	@ResponseBody
	public List<CommentVO> ComentListPost(Model model, @RequestBody int po_num) {
		List<CommentVO> list = gatPostService.getCommentList(po_num);
		model.addAttribute("list",list);
		return list;
		
	}
	
}