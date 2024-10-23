package kr.kh.petvely.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.kh.petvely.model.vo.BookMarkVO;
import kr.kh.petvely.model.vo.CommunityVO;
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
								BookMarkVO bookMarkVo,
								@PathVariable int co_num) {
		// 커뮤니티 리스트 가져오기
		List<CommunityVO> communityList = postService.selectCommunityList(); 
		model.addAttribute("communityList", communityList);
		if (communityList == null || communityList.isEmpty()) {
		    System.out.println("커뮤니티 리스트가 비어 있습니다.");
		}
		System.out.println(co_num);
		int me_num = 3; // << 추후에 로그인 추가하면 세션에서 따와야함
		
	    List<BookMarkVO> bookMarkList = new ArrayList<>();

	    if (co_num == 1) {
	        // WalkMatePost 조인
	        bookMarkList = bookmarkService.selectWalkMateBookmarks(me_num);
	    } else if (co_num == 2) {
	        // GATPost 조인
	        bookMarkList = bookmarkService.selectGATBookmarks(me_num);
	    } else if (co_num == 11) {
	        // MarketPost 조인
	        bookMarkList = bookmarkService.selectMarketBookmarks(me_num);
	    }

	    model.addAttribute("bookMarkList", bookMarkList);
		
		return "/bookmark/list";
	}
}
