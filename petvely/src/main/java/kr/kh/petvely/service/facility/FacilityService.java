package kr.kh.petvely.service.facility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.facility.FacilityDAO;
import kr.kh.petvely.model.vo.facility.FacilityShareTypeVO;
import kr.kh.petvely.model.vo.facility.FacilityShareVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FacilityService {

	@Autowired
	private FacilityDAO facilityDao;

	public List<FacilityShareTypeVO> getFacilityShareTypeList() {
		return facilityDao.selectFacilityShareTypeList();
	}

	public boolean insertFacilityShare(FacilityShareVO fsVo) {
		return facilityDao.insertFacilityShare(fsVo);
	}

	public List<FacilityShareVO> getFacilityShareList_withApprovl(boolean isApproval) {
		return facilityDao.selectFacilityShareList(isApproval);
	}

	public boolean approveFacilityShare(int fs_num) {
		return facilityDao.approveFacilityShare(fs_num);
	}	
}
	