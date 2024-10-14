package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.kh.petvely.model.vo.AnimalVO;
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
	public String walkmatepostInsert(Model model, AnimalVO animal) {
		// 로그인 기능 구현 완료 하면 me_num 서버에서 로그인 되어있는 아이디 가져오면 됨
		animal.setAni_me_num(2);
		List<AnimalVO> petList = animalService.selectPetList(animal);
		System.out.println(petList);
		model.addAttribute("petList", petList);
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
	public String walkmatepostUpdate(Model model, @PathVariable int po_num,
									 AnimalVO animal) {
		
		WalkMatePostVO walkMatePost = walkMatePostService.getWalkMatePost(po_num);
		model.addAttribute("walkMatePost", walkMatePost);
		
		List<AnimalVO> petList = animalService.selectPetList(animal);
		model.addAttribute("petList", petList);
		
		return "/walkmatepost/update";
	}
	
	@PostMapping("/walkmatepost/update/{po_num}")
	public String walkmatepostUpdatePost(Model model, 
										@PathVariable int po_num, 
										WalkMatePostVO walkMatePost,
										int [] selectedHostAniNums) {
		if(walkMatePostService.updateWalkMatePost(walkMatePost, selectedHostAniNums)) {
			System.out.println(walkMatePost);
			
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
