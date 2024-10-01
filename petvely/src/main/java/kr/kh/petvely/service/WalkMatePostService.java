package kr.kh.petvely.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.PostDAO;
import kr.kh.petvely.dao.WalkMatePostDAO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.WalkMatePostVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WalkMatePostService {
	
	private PostDAO postDao;
	
	private WalkMatePostDAO walkMatePostDao;

	public List<WalkMatePostVO> getWalkMatePostList() {
		return walkMatePostDao.selectWalkMatePostList();
	}

	public boolean insertWalkMatePost(PostVO post, WalkMatePostVO walkMatePost) {
		
		int po_num;
		
		if(post == null) {
			System.out.println("1");
			return false;
		}
		else {
			try {
				postDao.insertPost(post);
				System.out.println(post);
				po_num = post.getPo_num();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		if(walkMatePost ==  null) {
			System.out.println("2");
			return false;
		}
		try {
			walkMatePost.setPo_num(po_num);
			System.out.println(walkMatePost);
			return walkMatePostDao.insertWalkMatePost(walkMatePost);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public WalkMatePostVO getWalkMatePost(int po_num) {
		return walkMatePostDao.selectWalkMatePost(po_num);
		
	}

}
