package kr.kh.petvely.controller.facility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.petvely.model.vo.facility.FacilityShareTypeVO;
import kr.kh.petvely.model.vo.facility.FacilityShareVO;
import kr.kh.petvely.service.facility.FacilityService;
import kr.kh.petvely.utils.NoName;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/facility/")
public class FacilityShareController {
	private final String viewRoute = "view/facility/";
	private NoName noName = NoName.getInstance();

	@Autowired
	private FacilityService facilityService;
    
	private void log(String format, Object... arguments) {
    	format = "\n{}" + format;
		log.info(format, noName.getCurrentMethodName2(), arguments);		
    }
	
	@GetMapping("share")
	public String facilityShare(Model model) {
		log("");
		List<FacilityShareVO> fsVoList = facilityService.getFacilityShareList_withApprovl(true);
		
		System.out.println(fsVoList);
		
		model.addAttribute("fsVoList", fsVoList);
		
		return viewRoute + "user/favoriteMap";
	}
	
	@GetMapping("insert")
	public String facilityInsert(Model model) {
		log("");
		
		return viewRoute + "user/insertMap";
	}
	
	@GetMapping("popup/insert")
	public String facilityPopupInsert(Model model, FacilityShareVO fsVo) {
		log("{}", fsVo);
		
		List<FacilityShareTypeVO> fsTypeList = facilityService.getFacilityShareTypeList();
		
		model.addAttribute("fsTypeList", fsTypeList);
		model.addAttribute("fsVo", fsVo);
		
		return viewRoute + "popup/insert";
	}
	
	@ResponseBody
	@PostMapping("popup/insert")
	public String facilityPopupInsert_post(FacilityShareVO fsVo) {
		log(" : {}", fsVo);
		
		boolean result = facilityService.insertFacilityShare(fsVo);
		
		return "등록되었습니다.";
	}
	
	@GetMapping("admin/approval")
	public String facilityAdminApproval(Model model) {
		log("");
		
		List<FacilityShareVO> fsVoList = facilityService.getFacilityShareList_withApprovl(false);
		
		model.addAttribute("fsVoList", fsVoList);
		
		return viewRoute + "admin/approvalMap";
	}

	@ResponseBody
	@PostMapping("admin/approval")
	public void facilityAdminApproval_post(int fs_num
			, Model model) {
		log(" : {}", fs_num);
		
		boolean result = facilityService.approveFacilityShare(fs_num);
	}

	@ResponseBody
	@PostMapping("admin/favoriteMap")
	public Map<String, Object> facilityAdminFavoriteMap_post() {
		log("");
		
		List<FacilityShareVO> fsVoList = facilityService.getFacilityShareList_withApprovl(true);

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("fsVoList", fsVoList);
		
		return map;
	}
}