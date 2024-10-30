package kr.kh.petvely.controller.member;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.CommentVO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.service.member.MypageService;
import kr.kh.petvely.utils.NoName;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class Mypagecontroller{

	private final NoName util = NoName.getInstance();
	private MypageService mypageService;
	
	@GetMapping("/member/mypage")
	public String memberMypage() {
		log.info(util.getCurrentMethodName());
		util.getCurrentMethodName();
		return "member/mypage";
	}
	
	@GetMapping("/member/mypage/commentList")
	public String CommentList(Model model, @AuthenticationPrincipal CustomUser customUser) {
		MemberVO user = customUser.getMember();
		int me_num = user.getMe_num();
		List<CommentVO> list = mypageService.getCommentList(me_num);
		model.addAttribute("list", list);
		return "member/mypage/commentList";
	}
	
	@GetMapping("/member/mypage/postList/{co_num}")
	public String PostList(Model model, @AuthenticationPrincipal CustomUser customUser, @PathVariable int co_num) {
		MemberVO user = customUser.getMember();
		int me_num = user.getMe_num();
		List<CommunityVO> communities = mypageService.getCommunityList();
		model.addAttribute("communities", communities);
		return "member/mypage/postList";
	}
	
}
