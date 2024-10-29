package kr.kh.petvely.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.ReportDAO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportService {
	@Autowired
	private ReportDAO reportDao;
	
	public void insertReport(int rp_rtt_num, int rp_me_num, String rp_rt_type, String rp_rtt_type) {
		
		reportDao.insertReport(rp_rtt_num, rp_me_num, rp_rt_type, rp_rtt_type);
		
	}

}
