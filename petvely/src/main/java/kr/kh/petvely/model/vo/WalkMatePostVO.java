package kr.kh.petvely.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalkMatePostVO extends PostVO {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date wm_date;
	
	private String wm_time;
	private String wm_wms_state;
	
	@Override
	public String toString() {
		return "WalkMatePostVO [wm_date=" + wm_date + ", wm_time=" + wm_time + ", wm_wms_state=" + wm_wms_state +super.toString() + "]";
	}
	
}
