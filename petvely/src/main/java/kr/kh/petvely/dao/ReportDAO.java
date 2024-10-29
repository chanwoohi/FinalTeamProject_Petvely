package kr.kh.petvely.dao;

import org.apache.ibatis.annotations.Param;

public interface ReportDAO {

	boolean insertReport(@Param("rp_rtt_num")int rp_rtt_num,
						@Param("rp_me_num")int rp_me_num,
						@Param("rp_rt_type")String rp_rt_type,
						@Param("rp_rtt_type")String rp_rtt_type);

}
