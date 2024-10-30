package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.ReportDAO;
import kr.kh.petvely.model.vo.ReportTargetTypeVO;
import kr.kh.petvely.model.vo.ReportVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportService {
	@Autowired
	private ReportDAO reportDao;
	
	public void insertReport(int rp_rtt_num, int rp_me_num, String rp_rt_type, String rp_rtt_type, String rp_cause) {
		
		reportDao.insertReport(rp_rtt_num, rp_me_num, rp_rt_type, rp_rtt_type, rp_cause);
		
	}

	public List<ReportVO> getReportList(String rp_rtt_type) {
		return reportDao.selectReportList(rp_rtt_type);
	}

	public List<ReportTargetTypeVO> getRTTList() {
		return reportDao.selectRTTList();
	}

}
