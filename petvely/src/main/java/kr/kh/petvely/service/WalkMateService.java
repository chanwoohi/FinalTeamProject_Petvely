package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.WalkMateDAO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WalkMateService {
	@Autowired
	WalkMateDAO walkMateDao;

	//??? 얘 확인 필요
	public void approveSelectedMembers(List<Integer> selectedMembers) {
		
//		for (Integer aniNum : selectedMembers) {
//            walkMateDao.updateWmmApprove(aniNum);
//        }
//		
	}

	public void updateWalkMateMember(int[] num, int po_num) {
		
		for (Integer aniNum : num) {
            walkMateDao.updateWmmApprove(aniNum, po_num);
        }
		
	}

}
