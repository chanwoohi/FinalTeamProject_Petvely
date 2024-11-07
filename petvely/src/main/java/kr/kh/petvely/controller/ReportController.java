package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.ReportVO;
import kr.kh.petvely.service.PostService;
import kr.kh.petvely.service.ReportService;
import kr.kh.petvely.service.member.MemberService;

@Controller
public class ReportController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/report/post/{po_num}")
	public String reportPost(Model model,
							@AuthenticationPrincipal CustomUser customUser,
							@PathVariable int po_num) {
		
		// po_num 이용해서 post 정보 가져오기 만약 comment면 comment에 맞춰서
		PostVO post = postService.getPost(po_num);
		
		MemberVO member = memberService.getMeId(post.getPo_me_num());
		
		post.setMe_id(member.getMe_id());
		System.out.println(post.getMe_id());
		
		//작성자 post.getMe_id()만 바꾸면 알아서 적용
		model.addAttribute("writer", post.getMe_id());
		//내용 post.getPo_title()만 바꾸면 알아서 적용
		model.addAttribute("content", post.getPo_title());
		
		return "/report/post";
	}
	
	@PostMapping("/report/post/{po_num}")
	public String reportPost(@PathVariable int po_num,
							 @AuthenticationPrincipal CustomUser customUser,
							 @RequestParam("reason") String rp_rt_type,
							 @RequestParam("rp_cause") String rp_cause) {
		
		if( customUser != null ) {
			MemberVO user = customUser.getMember();
			// 여기선 po_num >> rp_rtt_num 
			// rp_rt_type = rp_rt_type, rp_rtt_type = post
			System.out.println("안녕 나오니???");
			int rp_me_num = user.getMe_num();
			
			String rp_rtt_type;
			
			// ex) comment 에서는 po_num << cm_num 이런식
			int rp_rtt_num = po_num;
			
			PostVO post = postService.getPost(po_num);
			System.out.println("po_num : " + po_num);
			System.out.println("po_co_num : " + post.getPo_co_num());
			
			System.out.println(post.getPo_co_num());
			if(post.getPo_co_num() == 10) {
				rp_rtt_type = "walkmatepost";
				reportService.insertReport(rp_me_num, rp_rtt_num, rp_rt_type, rp_rtt_type, rp_cause);
				System.out.println(rp_rtt_type);
			} else if (post.getPo_co_num() == 11) {
				rp_rtt_type = "marketpost";
				reportService.insertReport(rp_me_num, rp_rtt_num, rp_rt_type, rp_rtt_type, rp_cause);
				System.out.println(rp_rtt_type);
			} else if (post.getPo_co_num() == 12) {
				rp_rtt_type = "gatpost";
				reportService.insertReport(rp_me_num, rp_rtt_num, rp_rt_type, rp_rtt_type, rp_cause);
				System.out.println(rp_rtt_type);
			} else {
				rp_rtt_type = "post";
				reportService.insertReport(rp_me_num, rp_rtt_num, rp_rt_type, rp_rtt_type, rp_cause);
				System.out.println(rp_rtt_type);
			}
			
		}
		return "/close/close";
	}
	
	@GetMapping("/report/list")
	public String reportList(Model model) {
		
		List<ReportVO> list = reportService.getReportList();
		
		model.addAttribute("list", list);
		
		return "/report/list";
	}
	
	@GetMapping("/report/postview/{rp_num}")
	public String reportView(Model model,
							@PathVariable int rp_num) {
		
		ReportVO report = reportService.getReport(rp_num);
		
		System.out.println(report);
		
		model.addAttribute("report", report);
		
		return "/report/postview";
	}
	
}
