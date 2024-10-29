package kr.kh.petvely.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.CommentDAO;
import kr.kh.petvely.model.vo.CommentVO;
import kr.kh.petvely.model.vo.MemberVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
	
	private CommentDAO commentDao;
	
	public List<CommentVO> getCommentListin(int po_num) {
		return commentDao.selectCommentListin(po_num);
	}

	public boolean insertComment(CommentVO comment, MemberVO user) {
		if(comment == null || user == null) {
			return false;
		}
		comment.setMe_num(user.getMe_num());
		return commentDao.insertComment(comment);
	}
	
	public boolean insertComment1(CommentVO comment, MemberVO user) {
		if(comment == null || user == null) {
			return false;
		}
		comment.setMe_num(user.getMe_num());
		return commentDao.insertComment1(comment);
	}
	
	public boolean insertComment2(CommentVO comment, MemberVO user) {
		if(comment == null || user == null) {
			return false;
		}
		comment.setMe_num(user.getMe_num());
		return commentDao.insertComment2(comment);
	}

	public boolean deleteComment(CommentVO comment) {
		return commentDao.deleteComment(comment);
	}

	public CommentVO selectComment(int cm_num) {
		return commentDao.selectComment(cm_num);
	}

	public boolean updateComment(CommentVO comment) {
		if(comment == null) {
			return false;
		}
		try {
			return commentDao.updateComment(comment);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
