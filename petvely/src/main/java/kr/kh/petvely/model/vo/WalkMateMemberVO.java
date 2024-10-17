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
	private int ani_num;
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
	
	private int pusp_num; 
	private int pusp_po_num; 
	private int pusp_ani_num;
	
	private int me_num;
	private String me_id;
	private String me_pw;
	private String me_nickname;
	private String me_email;
	private String me_authority; 
	private String me_cookie;
	private Date me_limit;
	private String me_loginType;
	private String me_addressDetail;
	private String me_phone;
	private int me_wm_point;
	private int me_ga_point;
	private int me_mp_point;
	private int me_total_point;
	private int me_fakeReportNum;
	private String me_ms_status;
	
}
