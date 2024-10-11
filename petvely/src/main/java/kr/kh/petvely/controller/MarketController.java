package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.petvely.model.vo.FileVO;
import kr.kh.petvely.model.vo.GoodsTypeVO;
import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.service.GoodsService;
import kr.kh.petvely.service.MarketPostService;

@Controller
public class MarketController {

	@Autowired
	MarketPostService marketPostService;
	@Autowired
	GoodsService goodsService;

	
	@GetMapping("/post/market")
	public String postList(Model model) {

		List<MarketPostVO> list = marketPostService.getMarketList();
		List<FileVO> fileList = marketPostService.getThumNail();
		System.out.println(fileList);
		model.addAttribute("list",list);
		model.addAttribute("fileList",fileList);

		return "post/market";

}
	@GetMapping("/post/marketdetail/{po_num}")
	public String marketPostDetail(Model model, @PathVariable int po_num){
		PostVO post = marketPostService.getMarketPost(po_num);
		List<FileVO> fileList = marketPostService.getFileList(po_num);
		model.addAttribute("fileList",fileList);
		model.addAttribute("post",post);
		model.addAttribute("me_num", 2);
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
			return "redirect:/post/marketinsert";
		}
		return "redirect:/post/market";
		
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




