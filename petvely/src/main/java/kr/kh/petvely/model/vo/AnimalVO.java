package kr.kh.petvely.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalVO {
	private String ani_num;
	private String ani_name;
	private int ani_age;
	private String ani_gender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ani_birth;
	
	private float ani_weight;
	private String ani_img;
	private String ani_info;
	private int ani_neutralization;
	private String ani_at_type;
	private int ani_me_num;
	
	//PostHostSelectedPetsVO 와 join 해서 사용할 때 호출용
	private int phsp_num;
	private int phsp_po_num; 
	private int phsp_ani_num;

	//PostUserSelectedPetsVO 와 join 해서 사용할 때 호출용 
	private int pusp_num;
	private int pusp_po_num;
	private int pusp_ani_num;
}
