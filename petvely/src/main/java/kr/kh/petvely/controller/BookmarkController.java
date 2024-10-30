package kr.kh.petvely.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.BookMarkVO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.service.BookmarkService;
import kr.kh.petvely.service.PostService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BookmarkController {
	@Autowired
	private BookmarkService bookmarkService;
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/bookmark/list/{co_num}")
	private String bookmarkList(Model model,
								@PathVariable int co_num,
								@AuthenticationPrincipal CustomUser customUser) {
		// 커뮤니티 리스트 가져오기
		List<CommunityVO> communityList = postService.selectCommunityList(); 
		model.addAttribute("communityList", communityList);
		if (communityList == null || communityList.isEmpty()) {
		    System.out.println("커뮤니티 리스트가 비어 있습니다.");
		}
		System.out.println(co_num);
		MemberVO user = customUser.getMember();
		int me_num = user.getMe_num();
		
	    List<BookMarkVO> bookMarkList = new ArrayList<>();
	    
	    bookMarkList = bookmarkService.selectBookmarks(me_num, co_num);

	    model.addAttribute("bookMarkList", bookMarkList);
		
		return "/member/mypage";
	}
}
