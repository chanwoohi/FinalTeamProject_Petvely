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
		animal.setAni_me_num(2);
		return animalDao.selectPetList(animal);
	}
	

}
