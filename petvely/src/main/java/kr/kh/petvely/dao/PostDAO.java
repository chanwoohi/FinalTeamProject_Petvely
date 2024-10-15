package kr.kh.petvely.dao;

import org.apache.ibatis.annotations.Param;

import kr.kh.petvely.model.vo.PostVO;

public interface PostDAO {

	boolean insertPost(PostVO post);

	boolean deletePost(int po_num);

	boolean insertBookmark(@Param("po_num")int po_num, @Param("bm_me_num")int bm_me_num);
	
}
