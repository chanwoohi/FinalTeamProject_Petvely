package kr.kh.petvely.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kh.petvely.model.user.CustomUser;
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
	
	@GetMapping("/post/bookmark/{po_num}")
	private String postBookmark(Model model,
								@PathVariable int po_num) {
		// 나중엔 로그인 된 사용자의 me_num 그대로 가져오게 해야 함
		int bm_me_num = 3;
		
		if(postService.insertBookmark(po_num, bm_me_num)) {
			System.out.println("즐겨찾기 등록 성공!");
		} else {
			System.out.println("즐겨찾기 등록 실패!");
		}
		return "/home";
	}

	@GetMapping("/post/list/{co_num}")
	public String postList(Model model, @PathVariable int co_num) {
		
		// 게시글 목록을 가져와서 화면에 전달
		List<PostVO> list = postService.getPostList(co_num);
		// 커뮤니티 목록을 가져와서 화면에 전달
		List<CommunityVO> communities = postService.getCommunityList();
		
		System.out.println(list);
	
		// 카테고리 리스트를 가져옴
		model.addAttribute("list", list);
		model.addAttribute("communities", communities);
		model.addAttribute("co_num", co_num);  // 현재 선택된 카테고리 번호 전달

	    return "/post/list";
	}
	
	@GetMapping("/post/insert/{co_num}")
	public String postInsert(Model model, @PathVariable int co_num, @AuthenticationPrincipal CustomUser customUser) {
	    int meNum = customUser.getMeNum(); // 로그인된 사용자의 meNum 가져오기

	    // 모델에 사용자 정보 추가 (필요하면 me_id도 추가 가능)
	    model.addAttribute("me_num", meNum);

	    // 카테고리 목록을 모델에 추가
	    List<CommunityVO> communities = postService.getCommunityList();
	    model.addAttribute("communities", communities);

	    return "post/insert";
	}
	
	@PostMapping("/post/insert") // 글 작성 처리
	public String postInsertPost(Model model, PostVO post, @RequestParam("po_co_num") int co_num, @AuthenticationPrincipal CustomUser customUser) {
	    post.setPo_co_num(co_num); // 선택된 카테고리 설정

	    int meNum = customUser.getMeNum(); // 로그인된 사용자의 meNum 가져오기
	    post.setPo_me_num(meNum);  // 작성자의 meNum을 po_me_num에 설정
	    
	    // 모델에 사용자 정보 추가 (필요시 me_id도 추가 가능)
	    String meId = customUser.getMeId(); // 로그인된 사용자의 meId 가져오기
	    model.addAttribute("me_id", meId);

	    // 게시글 저장 처리
	    boolean res = postService.addPost(post);
	    System.out.println("po_me_num: " + post.getPo_me_num());
	    if (res) {
	        return "redirect:/post/list/" + co_num; // 성공 시 카테고리로
	    }
	    return "redirect:/post/insert"; // 실패 시 글쓰기 페이지로
	}

	
	@GetMapping("/post/detail/{po_num}") // 게시글 상세 조회
	public String postDetail(Model model, @PathVariable int po_num) {

	    postService.updateView(po_num); // 조회수 증가

	    PostVO post = postService.getPost(po_num);
	    model.addAttribute("post", post);

	    return "post/detail"; // 뷰 템플릿 반환
	}

	@GetMapping("/post/update/{po_num}") // 게시글 수정 페이지 이동, 카테고리 목록 전달
	public String postUpdate(Model model, @PathVariable int po_num) {
		
		PostVO post = postService.getPost(po_num); // 게시글 정보 가져오기		
		List<CommunityVO> communities = postService.getCommunityList(); // 카테고리 목록을 가져옴    

		model.addAttribute("post", post); // 게시글 정보 전달
		model.addAttribute("communities", communities); // 카테고리 목록 전달
		model.addAttribute("co_num", post.getPo_co_num());  // 선택된 카테고리 전달

		return "post/update"; // 뷰 템플릿 반환
	}
	@PostMapping("/post/update/{po_num}") //게시글 수정 페이지 글 작성 처리
	public String postUpdatePost(PostVO post, @PathVariable int po_num, @RequestParam("po_co_num")int co_num) {
		post.setPo_num(po_num);
		post.setPo_co_num(co_num); //선택된 카테고리를 게시글에 설정
		boolean res = postService.updatePost(post);
		if(res) {
			return "redirect:/post/detail/" + co_num; //성공 시 카테고리로
		}
		return "redirect:/post/update/" + po_num;
	}
	@GetMapping("/post/delete/{po_num}") // 삭제 (게시글 번호 받기)
	public String postDelete(Model model, @PathVariable int po_num, @AuthenticationPrincipal CustomUser customUser) {

	    // 로그인된 사용자 권한 확인
	    if (customUser == null) {
	        model.addAttribute("error", "로그인이 필요합니다.");
	        return "redirect:/member/login";  // 비회원일 경우 로그인 페이지로 리디렉션
	    }
	    
	    // 삭제 전에 커뮤니티 번호를 미리 가져옴
	    PostVO post = postService.getPost(po_num);
	    int co_num = post.getPo_co_num(); // 커뮤니티 번호 가져오기
	    
	    boolean res;
	    System.out.println("사용자 권한: " + customUser.getMember().getMe_authority());

	    // 삭제 로직 실행
	    System.out.println("ADMIN".equals(customUser.getMember().getMe_authority()));
	    if ("ADMIN".equals(customUser.getMember().getMe_authority())) {
	    	  System.out.println("관리자 권한 확인됨");
	        // 관리자 권한인 경우 논리적 삭제 처리
	        res = postService.logicalDeletePost(po_num);  // 논리적 삭제
	    } else {
	        // 일반 사용자일 경우 물리적 삭제 처리
	        res = postService.physicalDeletePost(po_num);  // 물리적 삭제
	    }

	    // 삭제가 성공했는지 확인
	    if (res) {
	        // 커뮤니티 목록으로 리디렉션
	        return "redirect:/post/list/" + co_num;  // 커뮤니티 번호로 리디렉션
	    } else {
	        // 삭제 실패 시 다시 상세보기로 리디렉션
	        return "redirect:/post/detail/" + po_num;
	    }
	}

	
	// 추천/비추천 처리
	@PostMapping("/post/recommend")
	public ResponseEntity<Map<String, Object>> recommend(
	        @RequestParam("state") int state, // 추천/비추천 상태
	        @RequestParam("num") int num,     // 게시글 번호
	        @AuthenticationPrincipal CustomUser customUser // 로그인한 사용자 정보
	) {
	    Map<String, Object> resultMap = new HashMap<>();

	    // 로그인 확인
	    if (customUser == null) {
	        resultMap.put("error", "로그인이 필요합니다.");
	        return ResponseEntity.badRequest().body(resultMap); // 400 에러 반환
	    }

	    try {
	        // 로그인된 사용자 정보를 사용
	        int me_num = customUser.getMember().getMe_num();  // 사용자 번호 가져오기
	        String me_id = customUser.getMember().getMe_id(); // 사용자 ID 가져오기

	        // 로그로 사용자 정보 출력
	        System.out.println("추천하는 사용자: " + me_id);

	        // 추천/비추천 처리
	        RecommendVO recommend = new RecommendVO();
	        recommend.setRe_po_num(num);  // 게시글 번호
	        recommend.setRe_me_num(me_num);  // 사용자 번호
	        recommend.setRe_state(state);  // 추천/비추천 상태

	        // 추천 정보 처리
	        int res = postService.insertRecommend(recommend);

	        // 게시글 정보 다시 가져오기
	        PostVO post = postService.getPost(num);

	        // 처리 결과를 응답으로 전송
	        resultMap.put("result", res);  // 추천 처리 결과
	        resultMap.put("post", post);   // 업데이트된 게시글 정보

	        return ResponseEntity.ok(resultMap);  // 성공적인 응답

	    } catch (Exception e) {
	        e.printStackTrace();
	        resultMap.put("error", "Exception 발생: " + e.getMessage());
	        return ResponseEntity.badRequest().body(resultMap);  // 예외 발생 시 400 에러 반환
	    }
	}


    

}
