package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.CommentVO;

public interface CommentDAO {

	List<CommentVO> selectCommentList();

	List<CommentVO> selectCommentListin(int po_num);

	boolean insertComment(CommentVO comment);
	
	boolean insertComment1(CommentVO comment);
	
	boolean insertComment2(CommentVO comment);

	boolean deleteComment(CommentVO comment);

	boolean updateComment(CommentVO comment);

	CommentVO selectComment(int cm_num);
}
