package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.FileVO;
import kr.kh.petvely.model.vo.PostVO;

public interface PostDAO {

	boolean insertPost(PostVO post);

	List<PostVO> selectPostList(int co_num);

	PostVO selectPost(int po_num);

	boolean updatePost(PostVO post);

	boolean deletePost(int po_num);

	List<CommunityVO> selectCommunityList();

	void insertFile(FileVO fileVo);

	void setIsDeleted(boolean Y);

}
