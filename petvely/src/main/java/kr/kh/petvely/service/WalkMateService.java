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

	public void approveSelectedMembers(List<Integer> selectedMembers) {
		
		for (Integer aniNum : selectedMembers) {
            walkMateDao.updateWmmApprove(aniNum);
        }
		
	}
}
