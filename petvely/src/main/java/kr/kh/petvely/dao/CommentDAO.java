package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.CommentVO;

public interface CommentDAO {

	List<CommentVO> selectCommentList();


}
