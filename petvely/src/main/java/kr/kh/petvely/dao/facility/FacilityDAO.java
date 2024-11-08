package kr.kh.petvely.dao.facility;

import java.util.List;

import kr.kh.petvely.model.vo.facility.FacilityShareTypeVO;
import kr.kh.petvely.model.vo.facility.FacilityShareVO;

public interface FacilityDAO {

	List<FacilityShareTypeVO> selectFacilityShareTypeList();

	boolean insertFacilityShare(FacilityShareVO fsVo);

	List<FacilityShareVO> selectFacilityShareList(boolean isApproval);

	boolean approveFacilityShare(int fs_num);
	
}
