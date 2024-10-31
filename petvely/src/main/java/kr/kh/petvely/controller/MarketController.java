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
	
	@GetMapping("/post/market/{co_num}")
	public String postList(Model model,@PathVariable int co_num) {
		
		List<MarketPostVO> list = marketPostService.getMarketList();
		
		model.addAttribute("list",list);
		

		return "post/market";

	}
	
	@GetMapping("/post/marketdetail/{po_num}")
	public String marketPostDetail(	Model model,
									@PathVariable int po_num,
									@AuthenticationPrincipal CustomUser CustomUser){
		if(CustomUser != null) {
			MarketPostVO post = marketPostService.getMarketPost(po_num);
			
			List<FileVO> fileList = marketPostService.getFileList(po_num);
			MemberVO user = CustomUser.getMember();
			int me_num = user.getMe_num();
			model.addAttribute("me_num",me_num);
			model.addAttribute("user", user);
			model.addAttribute("fileList",fileList);
			model.addAttribute("post",post);
			


			
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
			
			// 찜 & 즐겨찾기 버튼에 써야해서(일관성)
			model.addAttribute("po_num", po_num);
		
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
	@PostMapping("/post/marketinsert/{co_num}")
	public String marketPostInsertPost(MarketPostVO marketPost, MultipartFile[] fileList,
										@PathVariable int co_num,
										@AuthenticationPrincipal CustomUser CustomUser
										) {
		if(CustomUser != null) {
			MemberVO user = CustomUser.getMember();
			int me_num = user.getMe_num();
			marketPost.setPo_me_num(me_num);
			marketPost.setMp_gts_state("판매중");	
			boolean res = marketPostService.addPost(marketPost,fileList);
			if(res) {
				marketPost.getPo_co_num();
//				System.out.println("me_num:"+me_num);
//				System.out.println(marketPost);
//				System.out.println("post:"+post);
				return "redirect:/post/market/{co_num}";
			}
		}
		return "redirect:/post/market{co_num}";
		
	}
	
	@PostMapping("/post/marketcomplete/{po_num}")
	public String marketComplete(@PathVariable int po_num,MarketPostVO marketPost) {
		
			boolean res = marketPostService.marketComplete(po_num);
		
		if(res) {
				return "redirect:/post/market/" + marketPost.getPo_co_num();
		
			}
		return "redirect:/post/marketdetail/" + po_num;
	}

	@GetMapping("/post/marketupdate/{po_num}")
	public String marketUpdate(@PathVariable int po_num,Model model) {
		MarketPostVO marketPost = marketPostService.getMarketPost(po_num);
		model.addAttribute("marketPost",marketPost);
		List<GoodsTypeVO> types = goodsService.getTypes();
		List<FileVO> files = marketPostService.getFileList(po_num);
		model.addAttribute("files",files);
		model.addAttribute("types",types);
		System.out.println("파일"+files);

		return "post/marketupdate";
		
	}
	@PostMapping("/post/marketupdate/{po_num}")
	public String marketUpdatePost(@PathVariable int po_num,Model model, 
									MarketPostVO marketPost, MultipartFile[] fileList,int[] nums) {
		
		marketPost.setPo_num(po_num);
		marketPost.setPo_co_num(11);
		boolean res = marketPostService.updateMarketPost(marketPost,fileList,nums);
		
		System.out.println("업데이트할때 : "+marketPost);
		if(res) {
			return "redirect:/post/market/" + marketPost.getPo_co_num();
		}
		return "redirect:/post/marketupdate/{po_num}";
	}
}




