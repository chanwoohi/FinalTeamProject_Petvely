package kr.kh.petvely.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalVO {
	private String ani_num;
	private String ani_name;
	private int ani_age;
	private String ani_gender;
	private Date ani_birth;
	private float ani_weight;
	private String ani_img;
	private String ani_info;
	private int ani_neutralization;
	private String ani_at_type;
	private int ani_me_num;
	@Override
	public String toString() {
		return "{ ani_num : " + ani_num /*+ ", ani_name=" + ani_name + ", ani_age=" + ani_age + ", ani_gender="
				+ ani_gender + ", ani_birth=" + ani_birth + ", ani_weight=" + ani_weight + ", ani_img=" + ani_img
				+ ", ani_info=" + ani_info + ", ani_neutralization=" + ani_neutralization + ", ani_at_type="
				+ ani_at_type + ", ani_me_num=" + ani_me_num + "]"*/ +"}";
	}
	
	

}
