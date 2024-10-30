package kr.kh.petvely.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.kh.petvely.model.vo.ReportTargetTypeVO;
import kr.kh.petvely.model.vo.ReportVO;
import kr.kh.petvely.service.ReportService;

@Controller
public class AdminController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("member/admin/{rp_rtt_type}")
	public String memberAdmin(Model model,
							  @PathVariable String rp_rtt_type) {
		// 신고 유형 리스트 가져오기
		List<ReportTargetTypeVO> rttList = reportService.getRTTList();
		
		model.addAttribute("rttList", rttList);
		
		// 신고 목록 가져오기 < url에 맞춰서
		List<ReportVO> reportList = reportService.getReportList(rp_rtt_type);
		
		System.out.println(reportList);
		
		model.addAttribute("reportList", reportList);
		
		return "/member/admin";
	}
}
