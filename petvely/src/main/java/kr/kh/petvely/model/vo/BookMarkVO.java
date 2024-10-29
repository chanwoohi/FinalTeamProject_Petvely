package kr.kh.petvely.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookMarkVO {
	// BookMarkVO
	private int bm_num; 
	private int bm_me_num;
	private int bm_po_num;
	
	// GATPostVO
	private String gat_gatt_type;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date gat_startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date gat_endDate;
	
	private String gat_gat;
	private String gat_gats_state; 
	private int gat_emd_num;
	
	// MarketPostVO
	private String mp_gts_state;
	private String mp_name;
	private String mp_content;
	private int mp_price;
	private String mp_gt_type;
	
	// WalkMatePostVO
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date wm_date;
	
	private String wm_time;
	private String wm_wms_state;
	
	// PostVO
	private int po_num;
	private String po_title;
	private String po_content;
	private Date po_date;
	private String po_hidden;
	private int po_viewCount; 
	private int po_recommendCount;
	private int po_reportCount;
	private String po_notice;
	private int po_me_num;
	private int po_co_num;
	
	private String me_id;
}
