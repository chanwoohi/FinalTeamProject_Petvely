package kr.kh.petvely.pagination;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCriteria extends Criteria {
	private int co_num;

	@Override
	public String toString() {
		return "PostCriteria [co_num=" + co_num +","+ super.toString()+ "]";
	}
	
}
