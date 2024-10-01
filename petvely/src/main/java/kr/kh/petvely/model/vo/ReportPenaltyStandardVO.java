package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportPenaltyStandardVO {

	private String rps_standard;
	private Date rps_period;
	private int rps_number;
}
