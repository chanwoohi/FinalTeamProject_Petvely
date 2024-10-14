package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.AnimalDAO;
import kr.kh.petvely.model.vo.AnimalVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnimalService {
	
	@Autowired
	AnimalDAO animalDao;
	
	public List<AnimalVO> selectPetList(AnimalVO animal) {
		
		return animalDao.selectPetList(animal);
	}

	public List<AnimalVO> selectDetailPetList(int po_num) {
		
		return animalDao.selectDetailPetList(po_num);
	}

	public List<AnimalVO> selectChoicePetList(int po_num) {
		
		return animalDao.selectChoicePetList(po_num);
	}
	

}
