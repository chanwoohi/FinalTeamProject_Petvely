package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentVO {
	private int cm_num; 
	private String cm_content; 
	private Date cm_date; 
	private int cm_state; 
	private int cm_reportCount; 
	private int cm_me_num; 
	private int cm_po_num;
	private int cm_reply; 
	private int cm_ord; 
	private int cm_layer;
}
