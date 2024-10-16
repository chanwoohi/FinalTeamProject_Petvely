package kr.kh.petvely.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarketPostVO extends PostVO{
	private String mp_gts_state;
	private String mp_name;
	private String mp_content;
	private int mp_price;
	private String mp_gt_type;
	private String imgUrl;
}