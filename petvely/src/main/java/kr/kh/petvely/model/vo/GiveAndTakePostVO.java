package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GiveAndTakePostVO {
	private int po_num;
	private String gat_gatt_type;
	private Date gat_startDate;
	private Date gat_endDate;
	private String gat_gat;
	private String gat_gats_state; 
	private int gat_emd_num;
}
