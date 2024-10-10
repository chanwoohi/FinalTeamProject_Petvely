package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.FileVO;
import kr.kh.petvely.model.vo.PostVO;

public interface PostDAO {

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

	void updateView(int po_num);

}
