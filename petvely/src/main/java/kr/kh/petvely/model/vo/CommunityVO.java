package kr.kh.petvely.model.vo;

import kr.kh.petvely.model.vo.CommunityVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommunityVO {
	int co_num;
	String co_name;
	int co_count;
}
