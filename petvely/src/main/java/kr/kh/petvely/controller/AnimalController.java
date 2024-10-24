package kr.kh.petvely.controller;


import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.petvely.model.user.CustomUser;
import kr.kh.petvely.model.vo.AnimalVO;
import kr.kh.petvely.model.vo.MemberVO;
import kr.kh.petvely.service.AnimalService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AnimalController {
	@Autowired
	private AnimalService animalService;
	
	@GetMapping("/animal/signup")
	public String animalInsert() {
		
		return "/animal/signup";
	}
	
	@PostMapping("/animal/signup")
	public String animalInsertPost(@AuthenticationPrincipal CustomUser customUser,
	                               AnimalVO animalVo,
	                               MultipartFile file) {
		// 넘어오는지 확인용
		System.out.println(animalVo);
		
	    if (customUser != null) {
	        MemberVO user = customUser.getMember();

	        // 파일이 비어 있지 않은 경우에만 처리
	        if (!file.isEmpty()) {
	            try {
	                // 파일을 특정 경로에 저장
	                String filePath = "D:\\git\\FinalTeamProject\\petvely\\src\\main\\resources\\static\\uploads\\" + file.getOriginalFilename(); // 저장할 경로 설정
	                file.transferTo(new File(filePath)); // 파일 저장

	                // VO에 파일 경로 저장
	                animalVo.setAni_img(filePath); // 파일 경로를 VO에 저장
	            } catch (IOException e) {
	                e.printStackTrace();
	                return "redirect:/error";
	            }
	        }

	        // MyPet 등록
	        if (animalService.insertMyPet(animalVo, user.getMe_num())) {
	            System.out.println("마이펫 등록 성공 !");
	            return "redirect:/member/mypage/13";
	        }
	    }
	    System.out.println("마이펫 등록 실패 !");
	    return "redirect:/member/mypage/13";
	}
	
}
