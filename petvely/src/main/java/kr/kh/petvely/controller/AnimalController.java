package kr.kh.petvely.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.petvely.model.vo.Emd_AreasVO;
import kr.kh.petvely.model.vo.Sido_AreasVO;
import kr.kh.petvely.model.vo.Sigg_AreasVO;
import kr.kh.petvely.service.AddressService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AnimalController {
	@GetMapping("/animal/signup")
	public String animalInsert() {
		
		return "/animal/signup";
	}
	
}
