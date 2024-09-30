package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class PostVO {

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
}
