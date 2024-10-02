package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.kh.petvely.model.vo.GiveAndTakePostVO;
import kr.kh.petvely.service.GATPostService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class GATPostController {
	
	@Autowired
	private GATPostService gatPostService;
	
	@GetMapping("/gatpost/list")
	public String GTAPostlist(Model model) {
		List<GiveAndTakePostVO> list = gatPostService.getGATPostList();
		model.addAttribute("list", list);
		return "gatpost/list";
	}
	
	@GetMapping("/gatpost/detail/{po_num}")
	public String postDetail(Model model, @PathVariable int po_num) {
		GiveAndTakePostVO GiveAndTakePost = gatPostService.getGATPost(po_num);
		model.addAttribute("GiveAndTakePost", GiveAndTakePost);
		System.out.println(GiveAndTakePost);
		return "gatpost/detail";
	}
	
	@GetMapping("/gatpost/insert")
	public String GATPostInsert() {
		return "gatpost/insert";
	}
	
	@PostMapping("/gatpost/insert")
	public String GTAPostInsertPost(GiveAndTakePostVO GiveAndTakePost) {
		boolean res = gatPostService.addGATPost(GiveAndTakePost);
		System.out.println(GiveAndTakePost);
		if(res) {
			return "redirect:/gatpost/list";
		}
		return "redirect:/gatpost/insert";
	}
	
}
 