package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ReportVO {

	private int rp_num;
	private String rp_cause;
	private Date rp_date;
	private int rp_me_num;
	private String rp_rt_type;
	private String rp_rtt_type;
	private int rp_rtt_num;
}
