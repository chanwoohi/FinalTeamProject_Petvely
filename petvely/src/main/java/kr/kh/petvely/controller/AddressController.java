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
public class AddressController {

	private AddressService addressService;
	
	@GetMapping("/address/add")
	public String AddreccInsert(Model model) {
		List<Sido_AreasVO> sidoList = addressService.getSidoList();
		model.addAttribute("sidoList", sidoList);
		return "address/add";
	}
	
	@PostMapping("/get/sigg")
	@ResponseBody
	public List<Sigg_AreasVO> sigg(@RequestParam int sido_num) {
		List<Sigg_AreasVO> siggList = addressService.getSiggList(sido_num);
		return siggList;
	}
	
	@PostMapping("/get/emd")
	@ResponseBody
	public List<Emd_AreasVO> emd(@RequestParam int sigg_num) {
		List<Emd_AreasVO> emdList = addressService.getEmdList(sigg_num);
		return emdList;
	}
	
	
}
