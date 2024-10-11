package kr.kh.petvely.dao;

import kr.kh.petvely.model.vo.PostVO;

public interface PostDAO {

	boolean insertPost(PostVO post);

	boolean deletePost(int po_num);
	
}
