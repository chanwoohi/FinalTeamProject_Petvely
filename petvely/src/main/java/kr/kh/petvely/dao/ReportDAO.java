package kr.kh.petvely.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.petvely.model.vo.ReportTargetTypeVO;
import kr.kh.petvely.model.vo.ReportVO;

public interface ReportDAO {

	boolean insertReport(@Param("rp_me_num")int rp_me_num,
						@Param("rp_rtt_num")int rp_rtt_num,
						@Param("rp_rt_type")String rp_rt_type,
						@Param("rp_rtt_type")String rp_rtt_type,
						@Param("rp_cause")String rp_cause);

	List<ReportVO> selectReportList();

	List<ReportTargetTypeVO> selectRTTList();

	ReportVO selectReport(int rp_num);

}
