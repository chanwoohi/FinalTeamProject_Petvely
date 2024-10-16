package kr.kh.petvely.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendVO {

	private int re_num; 	//추천번호
	private int re_state; 	//추천상태 (1:추천 , -1:비추천) 
	private int re_me_num;  //추천한 사용자 ID
	private int re_po_num;	//추천한 게시글 번호
	
	// 1번 생성자 공부하기

}
