package kr.kh.petvely.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FacilityShareVO {
	private int fs_num; 
	private String fs_name; 
	private int fs_grade; 
	private int fs_reviewCount; 
	private int fs_reportCount; 
	private String fs_fst_type;
}	
