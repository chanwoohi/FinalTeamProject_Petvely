package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.BookMarkVO;

public interface BookmarkDAO {

	List<BookMarkVO> selectWalkMateBookmarks(int me_num);

	List<BookMarkVO> selectGATBookmarks(int me_num);

	List<BookMarkVO> selectMarketBookmarks(int me_num);

}
