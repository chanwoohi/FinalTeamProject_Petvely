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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.RecommendVO;
import kr.kh.petvely.service.PostService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PostController {
	@Autowired
	private PostService postService;
	
	@GetMapping("/post/bookmark/insert/{po_num}")
	private String postBookmarkInsert(Model model,
								@PathVariable int po_num,
								@AuthenticationPrincipal CustomUser customUser) {
		int bm_me_num;
		
		if(customUser != null) {
			
			MemberVO user = customUser.getMember();
			
			System.out.println(user.getMe_id());
			// 로그인 도입 후 변경 완료!
			bm_me_num = user.getMe_num();
			
			if(postService.insertBookmark(po_num, bm_me_num)) {
				System.out.println("즐겨찾기 등록 성공!");
			} else {
				System.out.println("즐겨찾기 등록 실패!");
			}
		}
		
		return "/home";
	}

	@GetMapping("/post/bookmark/delete/{po_num}")
	private String postBookmarkDelete(@PathVariable int po_num,
									@AuthenticationPrincipal CustomUser customUser) {
		MemberVO user = customUser.getMember();
		// 로그인 도입 후 변경 완료!
		int bm_me_num = user.getMe_num();
		
		if(postService.deleteBookmark(po_num, bm_me_num)) {
			System.out.println("즐겨찾기 취소 성공!");
		} else {
			System.out.println("즐겨찾기 취소 성공!");
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
	public String postInsertPost(Model model, PostVO post, 
								@RequestParam("po_co_num") int co_num,
								@AuthenticationPrincipal CustomUser customUser) {
	  
		post.setPo_co_num(co_num); // 선택된 카테고리 설정
	    int meNum = customUser.getMeNum(); // 로그인된 사용자의 meNum 가져오기
	    post.setPo_me_num(meNum);  // 작성자의 meNum을 po_me_num에 설정
	    
	    // 모델에 사용자 정보 추가 (필요시 me_id도 추가 가능)
	    String meId = customUser.getMeId(); // 로그인된 사용자의 meId 가져오기
	    model.addAttribute("me_id", meId);

	    // 게시글 저장 처리
	    boolean res = postService.addPost(post);
	    
	    if (res) {
	        return "redirect:/post/list/" + co_num; // 성공 시 카테고리로
	    }
	    return "redirect:/post/insert"; // 실패 시 글쓰기 페이지로
	}
	
	@GetMapping("/post/detail/{po_num}") // 게시글 상세 조회
	public String postDetail(Model model, @PathVariable int po_num, @AuthenticationPrincipal CustomUser customUser) {

	    postService.updateView(po_num); // 조회수 증가
	    PostVO post = postService.getPost(po_num);
	    MemberVO user = customUser.getMember();
	    model.addAttribute("post", post);
	    model.addAttribute("user", user);

	    return "post/detail"; // 뷰 템플릿 반환
	}

	@GetMapping("/post/update/{po_num}")
	public String postUpdate(Model model, @PathVariable int po_num,
	                         @AuthenticationPrincipal CustomUser customUser, 
	                         RedirectAttributes redirectAttributes) {
	    
	    PostVO post = postService.getPost(po_num);
	    List<CommunityVO> communities = postService.getCommunityList();

	    if (post == null) {
	        // 게시글이 없는 경우
	        redirectAttributes.addFlashAttribute("errorMessage", "존재하지 않는 게시글입니다.");
	        return "redirect:/post/list";  // 수정 페이지가 아닌 목록 페이지로 리디렉션
	    }

	    // 로그인된 사용자 권한 확인
	    if (!"ADMIN".equals(customUser.getMember().getMe_authority()) && post.getPo_me_num() != customUser.getMember().getMe_num()) {
	        redirectAttributes.addFlashAttribute("errorMessage", "수정 권한이 없습니다.");
	        return "redirect:/post/list/" + post.getPo_co_num();  // 수정 페이지가 아닌 목록 페이지로 리디렉션
	    }
	    
	    model.addAttribute("post", post);
	    model.addAttribute("communities", communities);
	    return "post/update";
	}
	@PostMapping("/post/update/{po_num}")
	public String postUpdatePost(@PathVariable int po_num, @RequestParam("po_co_num") int co_num,
								 PostVO post, @AuthenticationPrincipal CustomUser customUser) {
	    // 로그인 사용자와 게시글 작성자가 동일한지 확인 (또는 관리자)
	    PostVO existingPost = postService.getPost(po_num);
	    if (existingPost == null || (existingPost.getPo_me_num() != customUser.getMember().getMe_num())) {
	        return "redirect:/post/list/" + post.getPo_co_num();  // 권한이 없거나 게시글이 없으면 리스트로 리디엑션
	    }
	
	    // 수정된 내용 저장 처리
	    post.setPo_num(po_num);  // 수정할 게시글 번호 설정
	    post.setPo_co_num(co_num); //수정할 커뮤니티 번호
	    postService.updatePost(post);
	    
	    return "redirect:/post/list/" + post.getPo_co_num();  // 수정 완료 후 해당 페이지로 이동
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
	
	@GetMapping("/post/delete/{po_num}") // 삭제 (게시글 번호 받기)
	public String postDelete(Model model, @PathVariable int po_num, 
	                         @AuthenticationPrincipal CustomUser customUser) {

	    // 로그인된 사용자 권한 확인
	    if (customUser == null) {
	        model.addAttribute("error", "로그인이 필요합니다.");
	        return "redirect:/member/login";  // 비회원일 경우 로그인 페이지로 리디렉션
	    }

	    // 게시글 정보 가져오기
	    PostVO post = postService.getPost(po_num);
	    if (post == null) {
	        model.addAttribute("error", "존재하지 않는 게시글입니다.");
	        return "redirect:/post/list";  // 게시글이 없는 경우 목록으로 리디렉션
	    }

	    // 로그인된 사용자 정보
	    int me_num = customUser.getMember().getMe_num();  // 로그인된 사용자 번호 가져오기
	    String me_authority = customUser.getMember().getMe_authority(); // 로그인된 사용자 권한
	    int co_num = post.getPo_co_num(); // 커뮤니티 번호 가져오기

	    boolean res;


	    // 일반 사용자는 본인이 작성한 글만 삭제 가능
	    if (post.getPo_me_num() == me_num && "ADMIN".equals(me_authority)) {
	        res = postService.physicalDeletePost(po_num);  // 물리적 삭제
	    } 
	    // 관리자 권한이 있는 경우 논리적 삭제 처리
	    else if ("ADMIN".equals(me_authority)) {
		        res = postService.logicalDeletePost(po_num);  // 논리적 삭제
		    } 
	    else {
	        // 로그인된 사용자와 게시글 작성자가 다르면 삭제 불가
	        model.addAttribute("error", "삭제 권한이 없습니다.");
	        return "redirect:/post/detail/" + po_num;  // 권한이 없으면 다시 상세보기로 리디렉션
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


}
