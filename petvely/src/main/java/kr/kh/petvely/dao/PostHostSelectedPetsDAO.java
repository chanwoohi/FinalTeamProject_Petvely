package kr.kh.petvely.dao;

import kr.kh.petvely.model.vo.PostHostSelectedPetsVO;

public interface PostHostSelectedPetsDAO {

	void insertPostHostSelectedPets(PostHostSelectedPetsVO pet);

	void deletePostHostSelectedPets(int po_num);

}
