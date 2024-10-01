package kr.kh.petvely.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.WalkMatePostDAO;
import kr.kh.petvely.model.vo.WalkMatePostVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WalkMatePostService {
	
	private WalkMatePostDAO walkMatePostDao;

	public List<WalkMatePostVO> getWalkMatePostList() {
		return walkMatePostDao.selectWalkMatePostList();
	}

}
