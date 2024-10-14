package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalkMateMemberVO {

	private int wmm_num; 
	private String wmm_approve;
	private int wmm_po_num; 
	private int wmm_ani_num;
	private int wmm_me_num;
	
	
	// AnimalVO 가져오기
	private String ani_num;
	private String ani_name;
	private int ani_age;
	private String ani_gender;
	private Date ani_birth;
	private float ani_weight;
	private String ani_img;
	private String ani_info;
	private int ani_neutralization;
	private String ani_at_type;
	private int ani_me_num;
	
}
