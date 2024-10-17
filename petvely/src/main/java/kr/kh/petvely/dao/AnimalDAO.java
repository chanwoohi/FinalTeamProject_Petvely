package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.AnimalVO;

public interface AnimalDAO {

	List<AnimalVO> selectPetList(AnimalVO animal);

	List<AnimalVO> selectDetailPetList(int po_num);

	List<AnimalVO> selectChoicePetList(int po_num);

}
