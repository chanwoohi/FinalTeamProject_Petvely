package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.FileVO;
import kr.kh.petvely.model.vo.GoodsTypeVO;
import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.service.GoodsService;
import kr.kh.petvely.service.MarketPostService;
import kr.kh.petvely.service.PostService;

@Controller
public class MarketController {

	@Autowired
	MarketPostService marketPostService;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	PostService postService;
	
	@GetMapping("/post/market")
	public String postList(Model model) {

		List<MarketPostVO> list = marketPostService.getMarketList();
		List<FileVO> fileList = marketPostService.getThumNail();
		model.addAttribute("list",list);
		model.addAttribute("fileList",fileList);

		return "post/market";

	}
	
	@GetMapping("/post/marketdetail/{po_num}")
	public String marketPostDetail(Model model, @PathVariable int po_num,
								   @AuthenticationPrincipal CustomUser customUser){
		if(customUser != null) {
			// 즐겨찾기 기능
			MemberVO user = customUser.getMember();
			
			int bm_me_num = user.getMe_num();
			
			System.out.println("gatpost가 받는 po_num : " + po_num);
			Integer bookmark = postService.selectBookmark(bm_me_num, po_num);
			
			if (bookmark != null) {
				System.out.println("bookmark : " + bookmark);
				model.addAttribute("bookmark", bookmark);
			}
			
			PostVO post = marketPostService.getMarketPost(po_num);
			List<FileVO> fileList = marketPostService.getFileList(po_num);
			model.addAttribute("fileList",fileList);
			model.addAttribute("post",post);
			model.addAttribute("me_num", 1);
		}
		
		return "post/marketdetail";
	}
	
	@GetMapping("/post/marketinsert")
	public String marketPostInsert(Model model) {
		List<GoodsTypeVO> types = goodsService.getTypes();
		model.addAttribute("types",types);
		return "post/marketinsert";
	}
	
	@PostMapping("/post/marketinsert")
	public String marketPostInsertPost(MarketPostVO marketPost, MultipartFile[] fileList) {
		marketPost.setMp_gts_state("판매중");
		
		boolean res = marketPostService.addPost(marketPost,fileList);
		if(res) {
			return "redirect:/post/market";
		}
		return "redirect:/post/marketinsert";
		
	}
	
	@PostMapping("/post/marketcomplete/{po_num}")
	public String marketComplete(@PathVariable int po_num) {
		boolean res = marketPostService.marketComplete(po_num);
		
		if(res) {
			return "redirect:/post/market";
		}else {
			return "redirect:/post/marketdetail/" + po_num;
		}
	}


}




