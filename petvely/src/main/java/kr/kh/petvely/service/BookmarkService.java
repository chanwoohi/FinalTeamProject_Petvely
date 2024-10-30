package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.BookmarkDAO;
import kr.kh.petvely.model.vo.BookMarkVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookmarkService {
	
	@Autowired
	private BookmarkDAO bookmarkDao;

	public List<BookMarkVO> selectBookmarks(int me_num, int co_num ) {
		
		return bookmarkDao.selectBookmarks(me_num, co_num);
	}

}
