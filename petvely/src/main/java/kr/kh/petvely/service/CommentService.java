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
	
	public List<CommentVO> getCommentList() {
		return commentDao.selectCommentList();
	}
	
	public List<CommentVO> getCommentList(int po_num) {
		return commentDao.selectCommentList(po_num);
	}

	public boolean insertComment(CommentVO comment, MemberVO user) {
		if(comment == null || user == null) {
			return false;
		}
		comment.setMe_num(user.getMe_num());
		return commentDao.insertComment(comment);
	}

}
