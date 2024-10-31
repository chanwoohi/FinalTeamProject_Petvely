package kr.kh.petvely.dao;

import java.util.List;

import kr.kh.petvely.model.vo.Emd_AreasVO;
import kr.kh.petvely.model.vo.Sido_AreasVO;
import kr.kh.petvely.model.vo.Sigg_AreasVO;

public interface AddressDAO {

	List<Sido_AreasVO> selectSidoList();

	List<Sigg_AreasVO> selectSiggList(int sido_num);

	List<Emd_AreasVO> selectEmdList(int sigg_num);

	List<Sigg_AreasVO> selectAddressList(int emd_num);

}
