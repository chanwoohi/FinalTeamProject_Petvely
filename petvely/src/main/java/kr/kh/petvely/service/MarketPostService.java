package kr.kh.petvely.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.petvely.dao.MarketPostDAO;
import kr.kh.petvely.model.vo.FileVO;
import kr.kh.petvely.model.vo.MarketPostVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.utils.UploadFileUtils;



@Service
public class MarketPostService {

	@Autowired
	MarketPostDAO marketPostDao;
	
	@Value("${file.upload-dir}")
	String uploadPath;

	public List<MarketPostVO> getMarketList() {
		
		return marketPostDao.selectMarketList();
	}

	public MarketPostVO getMarketPost(int po_num) {
		return marketPostDao.selectPost(po_num);
	}

	public boolean addPost(MarketPostVO marketPost, MultipartFile[] fileList) {
		boolean res = false;
		if(marketPost == null) {
			return res;
		}try {
			marketPost.setPo_co_num(11);
			res = marketPostDao.insertPost(marketPost);
			if(res) {
				
			
			if(fileList == null || fileList.length == 0) {
				String defaultImage = "/image/image.jpg";
				marketPost.setMp_imgUrl(defaultImage);
				
			}else {
				for(MultipartFile file : fileList) {
					uploadFile(file, marketPost.getPo_num(),marketPost);
				}
				
			}
		}
			marketPostDao.insertMarketPost(marketPost);
		}catch(Exception e) {
			e.printStackTrace();
			return res;
		}
		return res;
	}
	private void uploadFile(MultipartFile file, int po_num, MarketPostVO marketPost) {
		if(file == null || file.getOriginalFilename().length() == 0) {
			return;
		}
		try {
			String fi_ori_name = file.getOriginalFilename();
			String fi_name = UploadFileUtils.uploadFile(uploadPath, fi_ori_name, file.getBytes());
			String basePath = "/uploads";
			String imgUrl = basePath + fi_name;
			marketPost.setMp_imgUrl(imgUrl);
			System.out.println("ImgURL " + imgUrl);
			
			FileVO fileVo = new FileVO(fi_ori_name, fi_name, po_num);
			
			marketPostDao.insertFile(fileVo);
			System.out.println("파일VO " +  fileVo);

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean marketComplete(int po_num) {
		
	    try {
	        marketPostDao.updateTradeState(po_num, "판매완료");
	        
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public List<FileVO> getFileList(int po_num) {

		return marketPostDao.selectFileList(po_num);
	}

	public boolean updateMarketPost(MarketPostVO marketPost, MultipartFile[] fileList, int[] nums) {
		if(marketPost == null) {
			return false;
		}
		boolean res = marketPostDao.updateMarketPost(marketPost);
		System.out.println("updateMarketPost : "+marketPost);
		if(!res) {
			return false;
		}
	
		uploadFileList(fileList,marketPost.getPo_num(), marketPost);
		if(nums == null || nums.length == 0) {
			return true;
		}
		for(int fi_num : nums) {
			FileVO file = marketPostDao.selectFile(fi_num,marketPost);
			deleteFile(file);
		}
		
		
		return true;
}
	

	

	private void deleteFile(FileVO file) {
		if(file == null) {
			return;
		}
		UploadFileUtils.delteFile(uploadPath, file.getFi_name());
		marketPostDao.deletFile(file.getFi_name());
		
	}

	private void uploadFileList(MultipartFile[] fileList, int po_num,MarketPostVO marketPost) {
		if(fileList == null || fileList.length == 0) {
			return;
		}
		for(MultipartFile file : fileList) {
			uploadFile(file, po_num, marketPost);
			
			System.out.println("uploadFile : " + marketPost);
		}
		
	}

	

}
