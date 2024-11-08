package kr.kh.petvely.model.vo.facility;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FacilityReviewVO {
	private int fr_num; 
	private String fr_content; 
	private int fr_grade; 
	private Date fr_date; 
	private int fr_report; 
	private int fr_fs_num; 
	private int fr_me_num;
}
