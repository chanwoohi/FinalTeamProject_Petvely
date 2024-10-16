package kr.kh.petvely.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.CommentDAO;
import kr.kh.petvely.model.vo.CommentVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
	
	private CommentDAO commentDao;
	
	public List<CommentVO> getCommentList() {
		return commentDao.selectCommentList();
	}
	
}
