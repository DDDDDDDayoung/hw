package kr.or.ddit.basic;

import java.util.List;

public interface BoardMVCService {
	
	public int registerPost(BoardMVCVO bv);
	
	public int modifyPost(BoardMVCVO bv);
	
	public int removePost(String boardNo);
	
	public List<BoardMVCVO> searchPost(BoardMVCVO bv);
	
	public List<BoardMVCVO> viewAll();
	
	public boolean checkPost(String boardNo);
	
	
}
