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
	private String wmm_ani_num;
	private int wmm_me_num;
}
