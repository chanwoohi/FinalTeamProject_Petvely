package kr.kh.petvely.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.petvely.model.vo.BookMarkVO;

public interface BookmarkDAO {

	List<BookMarkVO> selectWalkMateBookmarks(int me_num);

	List<BookMarkVO> selectGATBookmarks(int me_num);

	List<BookMarkVO> selectMarketBookmarks(int me_num);

}
