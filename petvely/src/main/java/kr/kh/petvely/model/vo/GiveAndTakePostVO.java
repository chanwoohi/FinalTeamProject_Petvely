package kr.kh.petvely.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GiveAndTakePostVO extends PostVO{
	
	private int po_num;
	private String gat_gatt_type;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date gat_startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date gat_endDate;
	private String gat_gat;
	private String gat_gats_state; 
	private int gat_emd_num;
	
	private String sido_name;
	private String sigg_name;
	private String emd_name;
	private int emd_num;
	
	private String gats_state;
	private String gatt_type;
	
	private int po_viewCount;
	
	private String me_id;
}
