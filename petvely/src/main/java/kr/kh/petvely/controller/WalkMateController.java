package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.petvely.service.WalkMateService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WalkMateController {
	
	@Autowired
	WalkMateService walkMateService;
	
	@PostMapping("/walkmate/approve")
	public String approveSelectedMembers(@RequestParam("selectedMembers") List<Integer> selectedMembers) {
	    if (selectedMembers == null || selectedMembers.isEmpty()) {
	        // 선택된 값이 없을 때 처리
	        System.out.println("선택된 값이 없습니다.");
	        return "redirect:/walkmatepost/detail/{po_num}";
	    }
	    
	    walkMateService.approveSelectedMembers(selectedMembers);
	    return "redirect:/walkmatepost/detail/{po_num}"; 
	}
	
}
