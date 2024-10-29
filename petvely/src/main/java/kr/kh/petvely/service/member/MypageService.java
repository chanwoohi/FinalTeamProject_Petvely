package kr.kh.petvely.service.member;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.member.MypageDAO;
import kr.kh.petvely.model.vo.CommentVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MypageService {

	private MypageDAO mypageDao;
	
	public List<CommentVO> getCommentList(int me_num) {
		return mypageDao.selectCommentList(me_num);
	}
}
