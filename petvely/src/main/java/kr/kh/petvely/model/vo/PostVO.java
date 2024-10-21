package kr.kh.petvely.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostVO {

	private int po_num; // 게시글 번호
	private String po_title; // 게시글 제목
	private String po_content; // 게시글 내용
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date po_date; // 작성일
	
	private String po_hidden; // 숨김여부
	private int po_viewCount; // 조회수
	private int po_recommendCount; // 추천수
	private int po_reportCount; // 신고수
	private String po_notice; // 공지
	private int po_me_num; // 작성자 ID (회원의 숫자)
	private int po_co_num; // 커뮤니티 번호
	private int po_delete; // 삭제처리

	private String me_id; //아이디
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
