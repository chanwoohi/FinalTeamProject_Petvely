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
		GiveAndTakePostVO GATPost = gatPostService.getGATPost(po_num);
		model.addAttribute("GATPost", GATPost);
		System.out.println(GATPost);
		return "gatpost/detail";
	}
	
	@GetMapping("/gatpost/insert")
	public String GATPostInsert() {
		return "gatpost/insert";
	}
	
	@PostMapping("/gatpost/insert")
	public String GTAPostInsertPost(GiveAndTakePostVO GATPost) {
		boolean res = gatPostService.addGATPost(GATPost);
		System.out.println(GATPost);
		if(res) {
			return "redirect:/gatpost/list";
		}
		return "redirect:/gatpost/insert";
	}
	
	@GetMapping("/gatpost/update/{po_num}")
	public String postUpdate(Model model, @PathVariable int po_num) {
		GiveAndTakePostVO GATPost = gatPostService.getGATPost(po_num);
		model.addAttribute("GATPost", GATPost);
		return "gatpost/update";
	}
	
	@PostMapping("/gatpost/update/{po_num}")
	public String postUpdatePost(Model model, @PathVariable int po_num, GiveAndTakePostVO GATPost) {
		GATPost.setPo_num(po_num);
		boolean res = gatPostService.updateGATPost(GATPost);
		if(res) {
			return "redirect:/gatpost/detail/"+po_num;
		}
		return "redirect:/gatpost/update/"+po_num;
	}
	
	@GetMapping("/gatpost/delete/{po_num}")
	public String postDelete(@PathVariable int po_num) {
		boolean res = gatPostService.deleteGATPost(po_num);
		if (res) {
			return "redirect:/gatpost/list";
		}
		return "redirect:/gatpost/detail/" + po_num;
	}
	
}
 