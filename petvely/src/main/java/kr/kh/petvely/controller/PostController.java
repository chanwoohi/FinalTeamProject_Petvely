package kr.kh.petvely.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.RecommendVO;
import kr.kh.petvely.service.PostService;
import lombok.AllArgsConstructor;

@Controller //json 형태로 객체 데이터 반환
@AllArgsConstructor
public class PostController {
	
	
	@Autowired
	private PostService postService;
	private ObjectMapper objectMapper;

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
			return "redirect:/post/detail/" + po_num;
		}
		return "redirect:/post/update/" + po_num;
	}
	@GetMapping("/post/delete/{po_num}") // 삭제 게시글 번호 받기
	public String postDelete(Model model, @PathVariable int po_num) {
	
			boolean res = postService.deletePost(po_num);
			if(res) {
			// 삭제한 게시글의 커뮤니티 번호를 가져와서 해당 커뮤니티로 리디렉션
			PostVO post = postService.getPost(po_num); // 삭제된 게시글 정보를 다시 가져옴
			int co_num = post.getPo_co_num(); // 커뮤니티 번호 가져오기
			return "redirect:/post/list/" + co_num;
		}
		return "redirect:/post/detail/" + po_num; // 삭제 실패 시 다시 상세보기로 리디렉션
	
	}
	@GetMapping("/listWithMember")  //게시글 목록과 작성자 ID 조회
	public String postListWithMemberId(Model model) {
		List<PostVO> postList = postService.getPostpostListWithMemberId();
		model.addAttribute("postList", postList);
		return "post/listWithMember";
	}
	// 추천/비추천 처리
    @GetMapping("/post/recommend")
    public ResponseEntity<Map<String, Object>> recommend(
    		
    		@RequestParam("state") int state, // 요청 파라미터 추천/비추천 상태를 받음
            @RequestParam("num") int num     // 요청 파라미터 게시글 번호를 받음
           
    ) {
        Map<String, Object> resultMap = new HashMap<>();
        
        try {
            // 임시 사용자 정보 설정
	        String me_id = "testUser"; //임시 사용자 아이디
	        int me_num = 1; // 임시 사용자 번호
       
        	// 게시글 번호와 추천 상태, 사용자 아이디를 기반으로 RecommendVO 객체 생성
        	RecommendVO recommend = new RecommendVO();
        	recommend.setRe_po_num(num); // 게시글 번호
        	recommend.setRe_me_num(me_num); //임시 사용자 번호
        	recommend.setRe_state(state); // 추천/비추천 상태
            
	        // 추천 정보 처리
	        int res = postService.insertRecommend(recommend);
	       
	        // 게시글 정보 다시 가져오기
	        PostVO post = postService.getPost(num);
	        
	        // PostVO 객체를 JSON 형식의 문자열로 변환
	        String postStr = objectMapper.writeValueAsString(post);
	        
	        resultMap.put("result", res); // 추천 처리 결과
	        resultMap.put("post", post); // 업데이트된 게시글 정보
	        
        } catch (Exception e) {
        	e.printStackTrace();
        	resultMap.put("error", "Exception 발생 : " + e.getMessage());
        }
        
        // 결과를 JSON 형식으로 반환
        return ResponseEntity.ok(resultMap);
    }

}