package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.kh.petvely.model.vo.BookMarkVO;
import kr.kh.petvely.service.BookmarkService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BookmarkController {
	@Autowired
	private BookmarkService bookmarkService;
	
	@GetMapping("bookmark/list")
	private String bookmarkList(Model model,
								BookMarkVO bookMarkVo) {
		bookMarkVo.setBm_me_num(3); // << 추후에 로그인 추가하면 세션에서 따와야함
		
		List<BookMarkVO> bookMarkList = bookmarkService.selectBookmarkList(bookMarkVo); 
		model.addAttribute("bookMarkList", bookMarkList);
		
		return "bookmark/list";
	}
}
