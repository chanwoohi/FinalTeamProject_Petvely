package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import kr.kh.petvely.model.vo.AnimalVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.model.vo.WalkMateMemberVO;
import kr.kh.petvely.model.vo.WalkMatePostVO;
import kr.kh.petvely.service.AnimalService;
import kr.kh.petvely.service.PostService;
import kr.kh.petvely.service.WalkMatePostService;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class WalkMatePostController {
	
	@Autowired
	private WalkMatePostService walkMatePostService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private AnimalService animalService;
	
	@GetMapping("/walkmatepost/list")
	public String walkmatepostList(Model model) {
		List<WalkMatePostVO> list = walkMatePostService.getWalkMatePostList();
		model.addAttribute("list", list);
		return "/walkmatepost/list";
	}
	
	@GetMapping("/walkmatepost/insert")
	public String walkmatepostInsert(Model model, AnimalVO animal, HttpSession session) {
	    // 세션에서 로그인된 사용자의 정보 가져오기
	    MemberVO loggedInUser = (MemberVO)session.getAttribute("user");
	    System.out.println("얘가 null이라는 거야??? : " + loggedInUser);
	    
	    
	    if (loggedInUser != null) {
	        // 로그인된 사용자 정보에서 me_num 가져와서 설정
	        animal.setAni_me_num(loggedInUser.getMe_num());

	        // 사용자의 애완동물 리스트를 가져옴
	        List<AnimalVO> petList = animalService.selectPetList(animal);
	        System.out.println(petList);
	        model.addAttribute("petList", petList);
	    } else {
	        // 로그인되지 않은 경우 처리 (예: 로그인 페이지로 리다이렉트)
	        return "redirect:/member/login";
	    }

	    return "/walkmatepost/insert";
	}
	
	@PostMapping("/walkmatepost/insert")
	public String walkmatepostInsertPost(WalkMatePostVO walkMatePost,
			                            int [] selectedHostAniNums){
		
		if(walkMatePostService.insertWalkMatePost(walkMatePost, selectedHostAniNums)) {
			return "redirect:/walkmatepost/list";
		}
		return "redirect:/walkmatepost/insert";

	}
	
	@GetMapping("/walkmatepost/detail/{po_num}")
	public String walkmatepostDetail(Model model, 
									@PathVariable int po_num, AnimalVO animal) {
		WalkMatePostVO walkMatePost = walkMatePostService.getWalkMatePost(po_num);
		model.addAttribute("walkMatePost", walkMatePost);
		
		List<AnimalVO> detailPetList = animalService.selectDetailPetList(po_num);
		model.addAttribute("detailPetList", detailPetList);
		
		// 로그인 기능 구현 완료 하면 me_num 서버에서 로그인 되어있는 아이디 가져오면 됨
		animal.setAni_me_num(3);
		List<AnimalVO> petList = animalService.selectPetList(animal);
		System.out.println(petList);
		model.addAttribute("petList", petList);
		
		List<AnimalVO> choicePetList = animalService.selectChoicePetList(po_num);
		model.addAttribute("choicePetList", choicePetList);
		
		List<WalkMateMemberVO> walkMateMember = walkMatePostService.selectWalkMateMember(po_num);
		model.addAttribute("walkMateMember", walkMateMember);
		
		System.out.println(walkMateMember);
				
		return "/walkmatepost/detail";
	}
	
	@PostMapping("/walkmatepost/detail/{po_num}")
	public String walkmatepostDetailPost(Model model,
										WalkMatePostVO walkMatePost,
										@PathVariable int po_num,
            							int [] selectedUserAniNums) {
		
		if(walkMatePostService.insertWalkMateMember(walkMatePost, selectedUserAniNums)) {
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
									 AnimalVO animal) {
		
		WalkMatePostVO walkMatePost = walkMatePostService.getWalkMatePost(po_num);
		model.addAttribute("walkMatePost", walkMatePost);
		
		List<AnimalVO> petList = animalService.selectPetList(animal);
		model.addAttribute("petList", petList);
		
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
		 * 상관 있는데 mysql 자체에서 설정해서 같이 삭제 시키게 했음
		 * 작동하면 다른 게시판에서 쓸 수 있으니까 postService로 보냄	
		*/ 
		
		if(postService.deletePost(po_num)) {
			return "redirect:/walkmatepost/list";
		}
		return "redirect:/walkmatepost/detail/"+po_num;
	}
	
}
