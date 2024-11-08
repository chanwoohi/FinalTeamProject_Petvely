package kr.kh.petvely.model.vo.facility;

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
	private String fs_info;
	private double fs_lat;
	private double fs_lng;
	
	@Override
	public String toString() {
		return "{fs_num:" + fs_num
				+ ", fs_name:'" + fs_name
				+ "', fs_grade:" + fs_grade
				+ ", fs_reviewCount:" + fs_reviewCount
				+ ", fs_reportCount:" + fs_reportCount
				+ ", fs_fst_type:'" + fs_fst_type
				+ "', fs_info:'" + fs_info
				+ "', fs_lat:" + fs_lat
				+ ", fs_lng:" + fs_lng + "}";
	}	
}	
