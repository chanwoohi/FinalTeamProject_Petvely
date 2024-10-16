package kr.kh.petvely.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.RecommendVO;
import kr.kh.petvely.service.PostService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PostController {
	
	
	@Autowired
	private PostService postService;


	@GetMapping("/post/list/{co_num}")
	public String postList(Model model, @PathVariable int co_num) {
		List<PostVO> list = postService.getPostList(co_num);
		//게시글 목록을 가져와서 화면에 전달
		List<CommunityVO> communities = postService.getCommunityList();
		//커뮤니티 리스트를 가져옴
		model.addAttribute("list", list);
		System.out.println(list);
		model.addAttribute("communities", communities);
	    return "/post/list";
	}
	@GetMapping("/post/insert/{co_num}") //글가져오기
	public String postInsert(@PathVariable int co_num) {
		return "post/insert";
	}
	@PostMapping("/post/insert") //글쓰기
	public String postInsertPost(PostVO post) {
		boolean res = postService.addPost(post);
		if(res) {
			return "redirect:/post/list/"+post.getPo_co_num();
		}
		return "redirect:/post/insert/"+post.getPo_co_num();
	}
	@GetMapping("/post/detail/{po_num}") //게시글 상세
	public String postDetail(Model model, @PathVariable int po_num) {
		postService.updateView(po_num);
		//조회수 증가
		PostVO post = postService.getPost(po_num);
		model.addAttribute("post", post);
		return "post/detail";	
	}
	@GetMapping("/post/update/{po_num}") //게시글 수정 가져오기
	public String postUpdate(Model model, @PathVariable int po_num) {
		PostVO post = postService.getPost(po_num);
		System.out.println(po_num);
		model.addAttribute("post", post);
		return "post/update";
	}
	@PostMapping("/post/update/{po_num}") //게시글 쓴거 저장
	public String postUpdatePost(Model model, @PathVariable int po_num, PostVO post) {
		post.setPo_num(po_num);
		boolean res = postService.updatePost(post);
		if(res) {
			return "redirect:/post/detail/"+po_num;
		}
		return "redirect:/post/update/"+po_num;
	}
	@GetMapping("/post/delete/{po_num}") //삭제
	public String postDelete(Model model, @PathVariable int po_num) {
	
			boolean res = postService.deletePost(po_num);
			if(res) {
			return "redirect:/post/list/"+po_num;
		}
		return "redirect:/post/detail/"+po_num;
	
	}
	@GetMapping("/listWithMember")  //게시글 목록과 작성자 ID 조회
	public String postListWithMemberId(Model model) {
		List<PostVO> postList = postService.getPostpostListWithMemberId();
		model.addAttribute("postList", postList);
		return "post/listWithMember";
	}
    // 추천/비추천 처리
    @ResponseBody
    @GetMapping("/post/recommend")
    public Map<String, Object> recommend(@RequestParam("state") int state,
                                         @RequestParam("num") int num) {  
    	
        Map<String, Object> resultMap = new HashMap<>();
        
        RecommendVO recommend = new RecommendVO();
        recommend.setRe_post_num(num);  // 게시글 번호
        recommend.setRe_state(state);  // 추천/비추천 상태

        // 추천/비추천 처리
        int res = postService.insertRecommend(recommend);

        // 게시글 정보 다시 가져오기
        PostVO post = postService.getPost(num);
        System.out.println(res);
        resultMap.put("result", res);  // 처리 결과(추천/비추천 또는 취소 상태)
        resultMap.put("post", post);           // 업데이트된 게시글 정보
        
        return resultMap;
    }


}