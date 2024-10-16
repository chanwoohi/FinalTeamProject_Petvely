package kr.kh.petvely.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.FileVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.RecommendVO;
import kr.kh.petvely.pagination.PostCriteria;

public interface PostDAO {
	
	public static final DataSource dataSource = null;
	
	boolean insertPost(PostVO post);

	// 게시글 목록 조회 (po_delete가 0인 것만)
	List<PostVO> selectPostList(int co_num);
	
	// 특정 게시글 조회 (po_delete가 0인 것만 메퍼 추후처리)
	PostVO selectPost(int po_num);

	boolean updatePost(PostVO post);

	boolean deletePost(int po_num);

	List<CommunityVO> selectCommunityList();

	void insertFile(FileVO fileVo);
	
	// 게시글 논리적 삭제 (po_delete 값을 1로 업데이트)
	boolean logicalDeletePost(int po_num);

	// Member의 me_id와 게시글 정보를 조회하는 메서드
	List<PostVO> selectPostWithMemberId();
	
	//조회수
	void updateView(int po_num);
    // 추천 정보를 가져오는 메서드
    RecommendVO selectRecommend(RecommendVO recommend);

    // 추천 정보를 추가하는 메서드
    int insertRecommend(RecommendVO recommend);

    // 추천 정보를 삭제하는 메서드
    int deleteRecommend(int re_num);

    // 게시글의 추천수를 업데이트하는 메서드
    int updateRecommendCount(int po_num);
    
    // 게시글 총 개수를 가져오는 메서드
	int selectCountPostList(int co_num);
    


}
