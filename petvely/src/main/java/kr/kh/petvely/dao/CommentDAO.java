package kr.kh.petvely.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.petvely.model.vo.CommentVO;

public interface CommentDAO {

	List<CommentVO> selectCommentList();

	List<CommentVO> selectCommentList(int po_num);

	boolean insertComment(CommentVO comment);
	
}
