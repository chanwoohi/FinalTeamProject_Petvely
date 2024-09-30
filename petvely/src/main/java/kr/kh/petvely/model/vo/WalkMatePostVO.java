package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalkMatePostVO {

	private int po_num;
	private Date wm_date;
	private String wm_time;
	private String wm_wms_state;
}
