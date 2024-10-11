package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileVO {
	int fi_num; 
	String fi_ori_name;
	String fi_name;
	Date fi_date;
	int fi_po_num;
	String imgUrl;

	public FileVO(String fi_ori_name, String fi_name, int fi_po_num) {
		this.fi_ori_name = fi_ori_name;
		this.fi_name = fi_name;
		this.fi_po_num = fi_po_num;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getImgUrl() {
		return imgUrl;
	}
}

