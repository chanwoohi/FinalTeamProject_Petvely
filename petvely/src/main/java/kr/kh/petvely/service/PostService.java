package kr.kh.petvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.petvely.dao.PostDAO;
import kr.kh.petvely.model.vo.CommunityVO;
import kr.kh.petvely.model.vo.FileVO;
import kr.kh.petvely.model.vo.PostVO;
import kr.kh.petvely.model.vo.RecommendVO;
import kr.kh.petvely.utils.UploadFileUtils;

@Service
public class PostService {
	@Autowired
	private PostDAO postDao;
	
	@Value("${file.upload-dir}")
	private String uploadPath;	
	
	public List<PostVO> getPostList(int co_num){
		return postDao.selectPostList(co_num);
		 // DAO를 통해 특정 커뮤니티 번호(co_num)에 해당하는 게시글을 가져옴
	}
	public boolean addPost(PostVO post, MultipartFile[] fileList) {
		if(post == null ||
		post.getPo_title().length() == 0 ||
		post.getPo_content().length() == 0) {
			return false;
		}
		
		boolean result = false;
		try {
			result = postDao.insertPost(post);
			uploadFileList(fileList, post.getPo_num());
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return result;
	}
	public PostVO getPost(int po_num) {
		return postDao.selectPost(po_num);
	}

	public boolean insertBookmark(int po_num, int bm_me_num) {
		
		return postDao.insertBookmark(po_num, bm_me_num);
		
	}

	public List<CommunityVO> selectCommunityList() {
		return postDao.selectCommunityList();
	}
	
	
	public boolean updatePost(PostVO post, MultipartFile[] fileList, int[] fileNumList) {
		if(post == null) {
			return false;
		}
		
		boolean result = false;
		
		try {
			result = postDao.updatePost(post);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		if (result == false)
			return false;

		result = uploadFileList(fileList, post.getPo_num());
		
		result = deleteFileList(fileNumList);
		
		return result;
	}
	//조회수 증가
	public void updateView(int po_num) {
		postDao.updateView(po_num);
		
	}
	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}

	// 커뮤니티 번호에 맞는 게시글 목록을 가져오는 메서드
	public List<PostVO> getAllPosts(int co_num) {
        return postDao.selectPostList(co_num);
    }		
	
	// 총 게시글 수를 가져오는 메서드 (페이징을 위해 사용)
	public int getPostCount(int co_num) {
        return postDao.selectCountPostList(co_num);
    }
	

	// 추천/비추천 처리 메서드
	public int insertRecommend(RecommendVO recommend) {
	    if (recommend == null) {
	        throw new RuntimeException("추천 정보가 null입니다.");
	    }
	
	    // 기존에 추천 여부 확인
	    RecommendVO dbRecommend = postDao.selectRecommend(recommend);
	
	    int po_num = recommend.getRe_po_num();  // 게시글 번호 가져오기
	
	    // 없으면 추가 후 추천 상태를 반환
	    if (dbRecommend == null) {
	        postDao.insertRecommend(recommend);  // 추천 추가
	    } else {
	        // 기존 상태와 새 상태가 같으면 추천을 취소
	        if (dbRecommend.getRe_state() == recommend.getRe_state()) {
	            postDao.deleteRecommend(dbRecommend.getRe_num());  // 추천 삭제
	            postDao.updateRecommendCount(po_num);  // 추천/비추천 수 갱신
	            return 0;  // 취소 상태 반환
	        }
	
	        // 기존 상태와 새 상태가 다르면 업데이트
	        postDao.deleteRecommend(dbRecommend.getRe_num());  // 기존 추천 삭제
	        postDao.insertRecommend(recommend);  // 새로운 추천 추가
	 }

	// 추천/비추천 수 업데이트
    postDao.updateRecommendCount(po_num);

    // 추천 상태 반환 (1: 추천, -1: 비추천)
    return recommend.getRe_state();
	}

	// 게시글 논리적 삭제 (po_delete 값을 1로 업데이트)
	public boolean logicalDeletePost(int po_num) {
	    return postDao.logicalDeletePost(po_num);  // 논리적 삭제
	}
	// 게시글 물리적 삭제
	public boolean physicalDeletePost(int po_num) {
	    return postDao.deletePost(po_num);  // 실제 삭제
	}
	public boolean deleteBookmark(int po_num, int bm_me_num) {
		return postDao.deleteBookmark(po_num, bm_me_num);
	}
	public Integer selectBookmark(int bm_me_num, int po_num) {
		
		return postDao.selectBookmark(bm_me_num, po_num);
	}
	public RecommendVO selectRecommendState(int me_num, int po_num) {
		
		return postDao.selectRecommendState(me_num, po_num);
		
	}
	public void insertPost(String po_title, String po_content, int co_num) {
		  postDao.insertPost(po_title, po_content, co_num);
	}

	private boolean uploadFileList(MultipartFile [] fileList, int po_num) {
		if(fileList == null || fileList.length == 0) {
			return false;
		}
		try {
			for(MultipartFile file : fileList) {
				uploadFile(file, po_num);
			}
		}catch (Exception e) {
			e.printStackTrace();
			postDao.deletePost(po_num);
			return false;
		}
		return true;
	}

	private void uploadFile(MultipartFile file, int po_num) {
		if(file == null || file.getOriginalFilename().length() == 0) {
			return;
		}
		
		try {
			System.out.println("" + uploadPath);
			String fi_ori_name = file.getOriginalFilename();
			String fi_name = UploadFileUtils.uploadFile(uploadPath, fi_ori_name, file.getBytes());
			
			FileVO fileVo = new FileVO(fi_ori_name, "/uploads" + fi_name, po_num);
			
			postDao.insertFile(fileVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<FileVO> getFileList(int po_num) {
		return postDao.selectFileList(po_num);
	}
	
	private boolean deleteFileList(int[] fileNumList) {
		if(fileNumList == null || fileNumList.length == 0) {
			return false;
		}
		try {
			for(int fi_num : fileNumList) {
				FileVO file = postDao.selectFile(fi_num);
				deleteFile(file);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void deleteFile(FileVO file) {
		if(file == null) {
			return;
		}
		UploadFileUtils.delteFile(uploadPath, file.getFi_name());
		postDao.deleteFile(file.getFi_num());
	}
}