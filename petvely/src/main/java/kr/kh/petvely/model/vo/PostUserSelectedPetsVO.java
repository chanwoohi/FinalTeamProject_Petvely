package kr.kh.petvely.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUserSelectedPetsVO {

	private int pusp_num; 
	private int pusp_po_num; 
	private int pusp_ani_num;
	
	
	// WalkMateMemberVO와 join 했을 때 getter, setter 사용하기 위해 추가
	private int wmm_num; 
	private String wmm_approve;
	private int wmm_po_num; 
	private int wmm_ani_num;
	private int wmm_me_num;
}
