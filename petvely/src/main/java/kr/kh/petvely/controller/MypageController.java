package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.AnimalVO;
import kr.kh.petvely.model.vo.CommentVO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.FileVO;
import kr.kh.petvely.model.vo.GiveAndTakePostVO;
import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.WalkMatePostVO;
import kr.kh.petvely.pagination.PageMaker;
import kr.kh.petvely.pagination.PostCriteria;
import kr.kh.petvely.service.AnimalService;
import kr.kh.petvely.service.GATPostService;
import kr.kh.petvely.service.MarketPostService;
import kr.kh.petvely.service.WalkMatePostService;
import kr.kh.petvely.service.member.MypageService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MypageController {
	
	@Autowired
	private AnimalService animalService;
	private MypageService mypageService;
	private GATPostService gatPostService;
	private MarketPostService marketPostService;
	private WalkMatePostService walkMatePostService;
	
	@GetMapping("/mypage/mypage")
	public String showProfilePage(Model model, @AuthenticationPrincipal CustomUser customUser) {
		if(customUser != null) {
			MemberVO user = customUser.getMember();
			AnimalVO animalVo = animalService.selectPetList(user.getMe_num()).getFirst();
			model.addAttribute("animalVo", animalVo);
		}
		
		model.addAttribute("currentPage", "user");
		return "/mypage/mypage";
	}
	
	@GetMapping("/mypage/pet")
	public String showgetProfilePage(Model model, @AuthenticationPrincipal CustomUser customUser) {
		model.addAttribute("currentPage", "pet");

		if(customUser != null) {
			MemberVO user = customUser.getMember();
			List<AnimalVO> petList = animalService.selectPetList(user.getMe_num());
			model.addAttribute("petList", petList);
		}
		
		return "/mypage/pet";
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
	public String PostList(Model model, @AuthenticationPrincipal CustomUser customUser, @PathVariable int co_num,PostCriteria cri) {
		MemberVO user = customUser.getMember();
		int me_num = user.getMe_num();
		List<CommunityVO> communities = mypageService.getCommunityList();
		List<PostVO> list = mypageService.getPostList(co_num, me_num);
		List<GiveAndTakePostVO> gatPostlist = mypageService.getGATPostList(me_num);
		List<MarketPostVO> maketList = mypageService.getMarketList(cri, me_num);
		PageMaker pm = marketPostService.getPageMaker(cri);
		List<WalkMatePostVO> walkList = mypageService.getWalkMatePostList(me_num);
		model.addAttribute("communities", communities);
		model.addAttribute("list", list);
		model.addAttribute("gatPostlist", gatPostlist);
		model.addAttribute("maketList",maketList);
		model.addAttribute("pm",pm);
		model.addAttribute("walkList", walkList);
		return "member/mypage/postList";
	}
	
	
}
