package kr.kh.petvely.model.vo;

import java.text.SimpleDateFormat;
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
	private int po_co_num;
	private int po_delete;

	private String me_id;
	
	// 새로 추가할 필드
    private int recommendCount; // 추천 수
    private int notRecommendCount; // 비추천 수

    // Getter, Setter
    public int getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }

    public int getNotRecommendCount() {
        return notRecommendCount;
    }

    public void setNotRecommendCount(int notRecommendCount) {
        this.notRecommendCount = notRecommendCount;
    }
	
	public String getPo_date() {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(po_date);
		
	}

	
}
