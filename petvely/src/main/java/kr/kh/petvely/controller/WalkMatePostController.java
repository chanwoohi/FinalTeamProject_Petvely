package kr.kh.petvely.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.AnimalVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.model.vo.WalkMateMemberVO;
import kr.kh.petvely.model.vo.WalkMatePostVO;
import kr.kh.petvely.service.AnimalService;
import kr.kh.petvely.service.PostService;
import kr.kh.petvely.service.WalkMatePostService;
import kr.kh.petvely.service.WalkMateService;


@Controller
public class WalkMatePostController {
	
	@Autowired
	private WalkMatePostService walkMatePostService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private WalkMateService walkMateService;
	
	@GetMapping("/walkmatepost/list")
	public String walkmatepostList(Model model) {
	    if (model.containsAttribute("msg")) {
	        model.addAttribute("msg", model.getAttribute("msg"));
	    }
	    List<WalkMatePostVO> list = walkMatePostService.getWalkMatePostList();
	    model.addAttribute("list", list);
		return "/walkmatepost/list";
	}
	
	@GetMapping("/walkmatepost/insert")
	public String walkmatepostInsert(Model model, AnimalVO animal
			,@AuthenticationPrincipal CustomUser customUser) {
		 System.out.println("등록 폼 페이지 모델 데이터: " + model.asMap());
		if(customUser != null) {
			MemberVO user = customUser.getMember();
			System.out.println(user.getMe_id() + user.getMe_num());
			
			List<AnimalVO> petList = animalService.selectPetList(user.getMe_num());
			System.out.println(petList);
			
			model.addAttribute("petList", petList);
			model.addAttribute("me_num", user.getMe_num());
		}
		return "/walkmatepost/insert";
	}
	
	@PostMapping("/walkmatepost/insert")
	@ResponseBody
	public Map<String, Object> walkmatepostInsertPost(WalkMatePostVO walkMatePost, int[] selectedHostAniNums) {
	    Map<String, Object> response = new HashMap<>();
	    if (walkMatePostService.insertWalkMatePost(walkMatePost, selectedHostAniNums)) {
	        response.put("success", true);
	        response.put("message", "게시글 등록에 성공하셨습니다.");
	    } else {
	        response.put("success", false);
	        response.put("message", "게시글 등록에 실패하셨습니다.");
	    }
	    return response;
	}
	
	@GetMapping("/walkmatepost/detail/{po_num}")
	public String walkmatepostDetail(Model model, 
									@PathVariable int po_num, 
									AnimalVO animal, 
									@AuthenticationPrincipal CustomUser customUser) {
		System.out.println("po_num : " + po_num);
		//조회수 증가
		postService.updateView(po_num);
		
		WalkMatePostVO walkMatePost = walkMatePostService.getWalkMatePost(po_num);
		model.addAttribute("walkMatePost", walkMatePost);
		
		List<AnimalVO> detailPetList = animalService.selectDetailPetList(po_num);
		model.addAttribute("detailPetList", detailPetList);
		
		if(customUser != null) {
			// 로그인 기능 구현 완료 하면 me_num 서버에서 로그인 되어있는 아이디 가져오면 됨
			MemberVO user = customUser.getMember();
			model.addAttribute("user", user);
			
			animal.setAni_me_num(user.getMe_num());

			List<AnimalVO> petList = animalService.selectPetList(user.getMe_num());
			System.out.println(petList);
			model.addAttribute("petList", petList);
			
			int bm_me_num = user.getMe_num();
			
			Integer bookmark = postService.selectBookmark(bm_me_num, po_num);
			if (bookmark != null) {
				System.out.println("bookmark : " + bookmark);
				model.addAttribute("bookmark", bookmark);
			}
			
		}
		
		model.addAttribute("po_num", po_num);
		
		List<AnimalVO> choicePetList = animalService.selectChoicePetList(po_num);
		model.addAttribute("choicePetList", choicePetList);
				
		return "/walkmatepost/detail";
	}
	
	@PostMapping("/walkmatepost/detail/{po_num}")
	public String walkmatepostDetailPost(Model model,
										WalkMatePostVO walkMatePost,
										@PathVariable int po_num,
            							int [] selectedUserAniNums,
										@AuthenticationPrincipal CustomUser customUser) {
		System.out.println("숫자배열 들어왔어?? : " + selectedUserAniNums);
		if(walkMatePostService.insertWalkMateMember(walkMatePost, selectedUserAniNums, customUser)) {
			List<WalkMateMemberVO> walkMateMemberList = walkMatePostService.selectWalkMateMember(po_num);
			
			System.out.println("walkMateMemberList : " + walkMateMemberList);
			model.addAttribute("walkMateMemberList", walkMateMemberList);
			
			return "redirect:/walkmatepost/detail/"+po_num;
		}
		return "redirect:/walkmatepost/detail/"+po_num;
	}
	
	@GetMapping("/walkmatepost/update/{po_num}")
	public String walkmatepostUpdate(Model model, 
									 @PathVariable int po_num,
									 AnimalVO animal,
									 @AuthenticationPrincipal CustomUser customUser) {
		
		WalkMatePostVO walkMatePost = walkMatePostService.getWalkMatePost(po_num);
		model.addAttribute("walkMatePost", walkMatePost);
		if(customUser!=null) {
			MemberVO user = customUser.getMember();
			List<AnimalVO> petList = animalService.selectPetList(user.getMe_num());
			model.addAttribute("petList", petList);
		}
		List<WalkMateMemberVO> walkMateMemberList = walkMatePostService.selectWalkMateMember(po_num);
		model.addAttribute("walkMateMemberList", walkMateMemberList);
		
		return "/walkmatepost/update";
	}
	
	@PostMapping("/walkmatepost/update/{po_num}")
	public String walkmatepostUpdatePost(Model model, 
										@PathVariable int po_num, 
										WalkMatePostVO walkMatePost,
										int [] selectedHostAniNums) {
		if(walkMatePostService.updateWalkMatePost(walkMatePost, selectedHostAniNums)) {
			System.out.println(walkMatePost);
			List<WalkMateMemberVO> walkMateMemberList = walkMatePostService.selectWalkMateMember(po_num);
			
			System.out.println("walkMateMemberList : " + walkMateMemberList);
			
			model.addAttribute("walkMateMemberList", walkMateMemberList);

			return "redirect:/walkmatepost/list";
		}
		return "redirect:/walkmatepost/detail/"+po_num;
	}
	
	@GetMapping("/walkmatepost/delete/{po_num}")
	public String walkmatepostDelete(Model model, @PathVariable int po_num) {
		/*
		 * postService에 맡긴 이유는 삭제 했을 때 DB에서 CASCADE 설정하면 어차피 같이 지워짐 ( po_num 공유라 상관 없나? )
		 * 상관 있는데 mysql 자체에서 cascade 설정해서 같이 삭제 시키게 했음
		 * 작동하면 다른 게시판에서 쓸 수 있으니까 postService로 보냄	
		*/ 
		
		if(postService.physicalDeletePost(po_num)) {
			return "redirect:/walkmatepost/list";
		}
		return "redirect:/walkmatepost/detail/"+po_num;
	}
	
	@GetMapping("/walkmatepost/approve/{po_num}")
	public String walkmatepostApprove(Model model, 
									  @PathVariable int po_num) {
		System.out.println("123123");
		List<WalkMateMemberVO> walkMateMember = walkMatePostService.selectWalkMateMember(po_num);
		model.addAttribute("walkMateMember", walkMateMember);
		
		System.out.println(walkMateMember);
		
		return "/walkmatepost/approve";
		
	}
	@PostMapping("/walkmatepost/approve/{po_num}")
	public String walkmatepostApprove(@PathVariable int po_num,
									  int [] selectedAniNums) {
		System.out.println("들어가긴하니??");
		walkMateService.updateWalkMateMember(selectedAniNums, po_num);
		
		boolean check = walkMatePostService.updateWalkmatePostState(po_num);
		if(check) System.out.println("상태 변경 성공");
		return "/walkmatepost/list";
	}
}
