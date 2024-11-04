package kr.kh.petvely.controller;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	    
	    // 나이 계산하는 코드
	    Date date = animalVo.getAni_birth();

        // Date를 LocalDate로 변환
        LocalDate birthDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();
        
        // 두 날짜 사이의 연도 차이를 계산하여 나이 계산
        long age = ChronoUnit.YEARS.between(birthDate, currentDate);
        
        System.out.println("나이: " + age);
        
        animalVo.setAni_age(age);
	    
	    
	    if (customUser != null) {
	        MemberVO user = customUser.getMember();

	        // 파일이 비어 있지 않은 경우에만 처리
	        if (file != null && !file.isEmpty()) {
	            try {
	                // UUID 생성
	                String uuid = UUID.randomUUID().toString();
	                // 파일명을 UUID와 원래 파일명으로 설정
	                String fileName = uuid + "_" + file.getOriginalFilename();
	                // 파일을 저장할 경로 설정
	                String filePath = "D:/uploads/" + fileName; // 파일 저장 경로
	                // 실제로 파일 저장
	                file.transferTo(new File(filePath)); // 파일 저장

	                // VO에 파일 경로 저장 (여기서 fileName을 사용해야 함)
	                animalVo.setAni_img("/uploads/" + fileName); // 상대 경로로 설정
	            } catch (IOException e) {
	                e.printStackTrace();
	                return "redirect:/error";
	            }
	        }

	        // MyPet 등록
	        if (animalService.insertMyPet(animalVo, user.getMe_num())) {
	            System.out.println("마이펫 등록 성공 !");
	            return "redirect:/mypage/pet";
	        }
	    }
	    System.out.println("마이펫 등록 실패 !");
	    return "redirect:/mypage/pet";
	}

	
	@GetMapping("/animal/delete/{ani_num}")
	public String animalDelete(@PathVariable int ani_num) {
		
		if(animalService.deleteMyPet(ani_num)) {
			System.out.println("펫 삭제 성공!");
		}
		System.out.println("펫 삭제 실패!");
		return "redirect:/mypage/pet";
	}
	
	@GetMapping("/animal/update/{ani_num}")
	public String animalUpdate(Model model,
							   @PathVariable int ani_num) {
		AnimalVO myPet = animalService.selectMyPet(ani_num);
		//잘 넘어왔는지 확인용
		System.out.println(myPet);
		model.addAttribute("myPet", myPet);
		
		return "/animal/update";
	}
	
	@PostMapping("/animal/update/{ani_num}")
	public String animalUpdatePost(@PathVariable int ani_num,
								   AnimalVO animal,
            					   MultipartFile file,
            					   @RequestParam String profileAction) {
		System.out.println("12");
		
	    // 나이 계산하는 코드
	    Date date = animal.getAni_birth();

        // Date를 LocalDate로 변환
        LocalDate birthDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();
        
        // 두 날짜 사이의 연도 차이를 계산하여 나이 계산
        long age = ChronoUnit.YEARS.between(birthDate, currentDate);
        
        System.out.println("나이: " + age);
        
        animal.setAni_age(age);
		
        // 새 프로필 사진 파일이 비어 있지 않은 경우에만 처리
        if (file != null && !file.isEmpty()) {
            try {
                // UUID 생성
                String uuid = UUID.randomUUID().toString();
                // 파일명을 UUID와 원래 파일명으로 설정
                String fileName = uuid + "_" + file.getOriginalFilename();
                // 파일을 저장할 경로 설정
                String filePath = "D:/uploads/" + fileName; // 파일 저장 경로
                // 실제로 파일 저장
                file.transferTo(new File(filePath)); // 파일 저장

                // VO에 파일 경로 저장 (여기서 fileName을 사용해야 함)
                animal.setAni_img("/uploads/" + fileName); // 상대 경로로 설정
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/error";
            }
        }
        if (profileAction.equals("useExisting")) {
            // 기존 이미지 사용
        	System.out.println("과연 ? : " + animal.getAni_img());
        	if(animal.getAni_img() == null) {
        		// 기존 이미지(ani.getAni_img())를 update에 넣을 animal 오브젝트에 넣기
        		AnimalVO ani = animalService.selectMyPet(ani_num);
        		animal.setAni_img(ani.getAni_img());
        	}
        } else if (profileAction.equals("discardExisting")) {
            // 이미지 무시하고 다른 처리
        }
        animal.setAni_num(ani_num);
        if(animalService.updateMyPet(animal)) {
        	System.out.println("ani"+animal);
        	System.out.println("마이펫 정보 수정 성공!");
        }else {
        	System.out.println("마이펫 정보 수정 실패!");
        }
		return "redirect:/mypage/pet";
	}
	
	@GetMapping("/animal/profile/{ani_num}")
	public String animalProfile(Model model,
								@PathVariable int ani_num) {
		AnimalVO myPet = animalService.selectMyPet(ani_num);
		// 확인용
		System.out.println(myPet);
		model.addAttribute("myPet", myPet);
		
		return "/animal/profile";
	}
	
}
