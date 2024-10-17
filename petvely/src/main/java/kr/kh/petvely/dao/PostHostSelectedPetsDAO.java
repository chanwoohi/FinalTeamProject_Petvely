package kr.kh.petvely.dao;

import kr.kh.petvely.model.vo.PostHostSelectedPetsVO;
import kr.kh.petvely.model.vo.PostUserSelectedPetsVO;

public interface PostHostSelectedPetsDAO {

	void insertPostHostSelectedPets(PostHostSelectedPetsVO pet);

	void deletePostHostSelectedPets(int po_num);

	void insertPostUserSelectedPets(PostUserSelectedPetsVO pet);

	void deletePostUserSelectedPets(int po_num);

}
