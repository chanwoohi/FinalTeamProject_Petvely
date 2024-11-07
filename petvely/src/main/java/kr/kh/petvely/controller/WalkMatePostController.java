package kr.kh.petvely.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.AnimalVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.model.vo.MessageVO;
import kr.kh.petvely.model.vo.Sido_AreasVO;
import kr.kh.petvely.model.vo.WalkMateMemberVO;
import kr.kh.petvely.model.vo.WalkMatePostVO;
import kr.kh.petvely.service.AddressService;
import kr.kh.petvely.service.AnimalService;
import kr.kh.petvely.service.MessageService;
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
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping("/walkmatepost/list")
	public String walkmatepostList(Model model) {
	    if (model.containsAttribute("msg")) {
	        model.addAttribute("msg", model.getAttribute("msg"));
	    }
	    List<WalkMatePostVO> list = walkMatePostService.getWalkMatePostList();
	    Collections.reverse(list);
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
			List<Sido_AreasVO> sidoList = addressService.getSidoList();
			model.addAttribute("petList", petList);
			model.addAttribute("me_num", user.getMe_num());
			model.addAttribute("sidoList", sidoList);
		}
		return "/walkmatepost/insert";
	}
	
	@PostMapping("/walkmatepost/insert")
	@ResponseBody
	public Map<String, Object> walkmatepostInsertPost(WalkMatePostVO walkMatePost, int[] selectedHostAniNums) {
	    Map<String, Object> response = new HashMap<>();
	    System.out.println(walkMatePost.getLatitude());
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
		
		List<Sido_AreasVO> sidoList = addressService.getSidoList();
		model.addAttribute("sidoList", sidoList);
		System.out.println("=================");
		System.out.println("walkMatePost : "+ walkMatePost);
		System.out.println("detailPetList : "+ detailPetList);
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
			List<Sido_AreasVO> sidoList = addressService.getSidoList();
			model.addAttribute("petList", petList);
			model.addAttribute("sidoList", sidoList);
			System.out.println("sidoList : " + sidoList);
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
									  int [] selectedAniNums,
									  @AuthenticationPrincipal CustomUser customUser) {
		
		walkMateService.updateWalkMateMember(selectedAniNums, po_num);
		System.out.println(Arrays.toString(selectedAniNums));
		
		walkMatePostService.updateWalkmatePostState(po_num);
		
		if(customUser != null) {
			MemberVO user = customUser.getMember();
			int senderNum = user.getMe_num();
			
		
				WalkMateMemberVO comer = walkMateService.getReceiverNum(selectedAniNums);
				System.out.println(comer);
				int receiverNum = comer.getAni_me_num();
				System.out.println("받는사람 : " + receiverNum);
				String content = "산책메이트가 승인 되었습니다.";
				
				MessageVO message = new MessageVO();
				message.setMes_me_senderNum(senderNum); 
				message.setMes_me_receiverNum(receiverNum); 
				message.setMes_content(content); 
				message.setMes_date(new Date()); 
				System.out.println("산책 msg : "+message);
				
				messageService.WalkMateMessage(message);
				
				return "/walkmatepost/list";
			}
			
		
		
		
		return "/walkmatepost/list";
	}
}
