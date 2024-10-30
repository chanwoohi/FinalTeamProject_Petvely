package kr.kh.petvely.controller.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.AnimalVO;
import kr.kh.petvely.model.vo.BookMarkVO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.service.AnimalService;
import kr.kh.petvely.service.BookmarkService;
import kr.kh.petvely.service.PostService;
import kr.kh.petvely.service.member.MemberService;
import kr.kh.petvely.utils.NoName;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/")
public class MemberController {
	private final String viewRoute = "view/member/";
	private final NoName util = NoName.getInstance();
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@GetMapping("signup")
	public String memberSignup(Model model) {
		log.info(util.getCurrentMethodName());
		return viewRoute + "signup";
	}
	
	@ResponseBody
	@PostMapping("check/value")
	public boolean memberCheckValue_Post(String type, String value) {
		log.info(util.getCurrentMethodName() + " : " + type + " : " + value);
		boolean result = memberService.checkRedundancy(type, value);
		log.info("" + result);
		return result;
	}
	
	@PostMapping("signup")
	public String memberSignup_Post(MemberVO memberVO) {
		log.info(util.getCurrentMethodName() + " : " + memberVO);
		boolean result = memberService.signup(memberVO);
		log.info("" + result);
		
		return "redirect:/";
	}
	
	@GetMapping("login")
	public String memberLogin() {
		log.info(util.getCurrentMethodName());
		return viewRoute + "login";
	}
	
	@GetMapping("mypage/{co_num}")
	public String myPage(Model model,
						 @AuthenticationPrincipal CustomUser customUser,
						 CommunityVO communityVo,
						 @PathVariable int co_num) {
		if(customUser != null) {
			// 커뮤니티 리스트 가져오기
			List<CommunityVO> communityList = postService.selectCommunityList();
			model.addAttribute("communityList", communityList);
			if (communityList == null || communityList.isEmpty()) {
			    System.out.println("커뮤니티 리스트가 비어 있습니다.");
			}
			model.addAttribute("co_num", co_num);
			
			MemberVO user = customUser.getMember();
			List<AnimalVO> petList = animalService.selectPetList(user.getMe_num());
			
			model.addAttribute("petList", petList);
			
			int me_num = user.getMe_num();
			
		    List<BookMarkVO> bookMarkList = new ArrayList<>();
		    
		    bookMarkList = bookmarkService.selectBookmarks(me_num, co_num);
	
		    model.addAttribute("bookMarkList", bookMarkList);
		
		
		}
		return "/member/mypage";
	}
	
	/*
	 * @PostMapping("login") public String memberLogin_post(Model model,
	 * MemberVO memberVO, HttpSession session) {
	 * log.info(util.getCurrentMethodName() + " : " + memberVO); MemberVO user =
	 * memberService.login(memberVO);
	 * 
	 * model.addAttribute("user", user); log.info("memberLogin_pos123123t : " +
	 * user); return "redirect:/"; }
	 * 
	 * @GetMapping("logout") public String memberLogout() {
	 * log.info(util.getCurrentMethodName());
	 * 
	 * return viewRoute + "logout"; }
	 */
}