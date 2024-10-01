package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {

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
