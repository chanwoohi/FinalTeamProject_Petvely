package kr.kh.petvely.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.petvely.dao.AddressDAO;
import kr.kh.petvely.model.vo.Emd_AreasVO;
import kr.kh.petvely.model.vo.GiveAndTakePostVO;
import kr.kh.petvely.model.vo.Sido_AreasVO;
import kr.kh.petvely.model.vo.Sigg_AreasVO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressService {
	
	private AddressDAO addressDAO;

	public List<Sido_AreasVO> getSidoList() {
		return addressDAO.selectSidoList();
	}

	public List<Sigg_AreasVO> getSiggList(int sido_num) {
		return addressDAO.selectSiggList(sido_num);
	}

	public List<Emd_AreasVO> getEmdList(int sigg_num) {
		return addressDAO.selectEmdList(sigg_num);
	}

	public List<Sigg_AreasVO> getAddressList(int emd_num) {
		return addressDAO.selectAddressList(emd_num);
	}


}
