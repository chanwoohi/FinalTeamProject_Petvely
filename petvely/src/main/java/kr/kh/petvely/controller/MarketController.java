package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.service.MarketPostService;

@Controller
public class MarketController {

	@Autowired
	MarketPostService marketPostService;
	
	@GetMapping("/post/market")
	public String postList(Model model) {
	
		List<MarketPostVO> list = marketPostService.getMarketList();

		model.addAttribute("list",list);

		return "post/market";

}
	@GetMapping("/post/marketdetail/{po_num}")
	public String marketPostDetail(Model model, @PathVariable int po_num){
		PostVO post = marketPostService.getMarketPost(po_num);
		model.addAttribute("post",post);
		return "post/marketdetail";
}
	@GetMapping("/post/marketinsert")
	public String marketPostInsert() {
		return "post/marketinsert";
	}
	@PostMapping("/post/marketinsert")
	public String marketPostInsertPost(MarketPostVO marketPost) {
//	로그인 
		boolean res = marketPostService.addPost(marketPost);
		if(res) {
			return "redirect:/post/marketinsert";	
		}
		return "redirect:/post/market";
		
	}
	
}




