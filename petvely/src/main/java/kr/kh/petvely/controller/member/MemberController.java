package kr.kh.petvely.controller.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import kr.kh.petvely.model.dto.MessageDTO;
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
		
		boolean result = false;
		
		result = memberService.checkRedundancy(type, value);
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
	
	@PostMapping("failed")
	public String memberFailed_post(Model model, HttpServletRequest request) {
		log.info(util.getCurrentMethodName());

		Exception error =  (Exception)request.getAttribute("error");
		log.info("{}", error.getMessage());
		
    	if(error instanceof AuthenticationServiceException) {
    		model.addAttribute("message", new MessageDTO("/", "정지되었거나 존재하지 않는 계정입니다."));
    	}
    	else {
    		model.addAttribute("message", new MessageDTO("/", "로그인에 실패했습니다.\\n아이디 또는 비밀번호를 확인해주세요."));
    	}
    	
		return "view/main/message";
	}

	@GetMapping("update")
	public String memberUpdate(Model model, @AuthenticationPrincipal CustomUser customUser) {
		log.info(util.getCurrentMethodName() + " : " + customUser.getMember());
		
	    model.addAttribute("memberVo", customUser.getMember());
		return viewRoute + "update";
	}
}