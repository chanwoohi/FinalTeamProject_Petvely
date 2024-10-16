package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContentReviewVO {
	private int cr_num;
	private String cr_content;
	private int cr_grade;
	private Date cr_date;
	private int cr_report;
	private int cr_me_num;
	private int cr_po_num;
}
