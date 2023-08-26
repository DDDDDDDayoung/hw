package kr.or.ddit.basic;

import java.util.List;

public class BoardMVCServiceImpl implements BoardMVCService{
	
	private BoardMVCdao borDao;
	
	private static BoardMVCService borService;
	
	public BoardMVCServiceImpl() {
		borDao = new BoardMVCdaoImpl();
	}

	@Override
	public int registerPost(BoardMVCVO bv) {
		int cnt = borDao.insertPost(bv);
		
		return cnt;
	}

	@Override
	public int modifyPost(BoardMVCVO bv) {
		int cnt = borDao.updatePost(bv);
		
		return cnt;
	}

	@Override
	public int removePost(String boardNo) {
		int cnt = borDao.deletePost(boardNo);
		
		return cnt;
	}

	@Override
	public List<BoardMVCVO> searchPost(BoardMVCVO bv) {
		List<BoardMVCVO> borList = borDao.checkPost(bv);
		
		return borList;
	}

	@Override
	public List<BoardMVCVO> viewAll() {
		List<BoardMVCVO> borList = borDao.searchAll();
		
		return borList;
	}

	@Override
	public boolean checkPost(String boardNo) {
		boolean isExist = borDao.checkNo(boardNo);
		
		return isExist;
	}

}
