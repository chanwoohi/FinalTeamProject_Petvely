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


	public List<BookMarkVO> selectWalkMateBookmarks(int me_num) {
		
		return bookmarkDao.selectWalkMateBookmarks(me_num);
	}

	public List<BookMarkVO> selectGATBookmarks(int me_num) {
		return bookmarkDao.selectGATBookmarks(me_num);
	}

	public List<BookMarkVO> selectMarketBookmarks(int me_num) {
		return bookmarkDao.selectMarketBookmarks(me_num);
	}

}
