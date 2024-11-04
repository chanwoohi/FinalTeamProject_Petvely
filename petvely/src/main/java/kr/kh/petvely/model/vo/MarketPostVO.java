package kr.kh.petvely.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarketPostVO extends PostVO{
	private int po_num;
	private String mp_gts_state;
	private String mp_name;
	private String mp_content;
	private int mp_price;
	private String mp_gt_type;
	private String mp_imgUrl;
	public MarketPostVO(String imgUrl) {
		this.mp_imgUrl = imgUrl;
	}
}