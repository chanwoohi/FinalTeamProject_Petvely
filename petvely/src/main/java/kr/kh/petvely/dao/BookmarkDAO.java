package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.BookMarkVO;

public interface BookmarkDAO {

	List<BookMarkVO> selectBookmarks(int me_num, int co_num);

}
