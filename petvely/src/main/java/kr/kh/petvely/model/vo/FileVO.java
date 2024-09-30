package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileVO {
	private int fi_num; 
	private String fi_ori_name; 
	private String fi_name; 
	private Date fi_date; 
	private int fi_po_num;
}
