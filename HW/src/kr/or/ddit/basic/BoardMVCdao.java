package kr.or.ddit.basic;

import java.util.List;

public interface BoardMVCdao {
	
	public int insertPost(BoardMVCVO bv);
	
	public int updatePost(BoardMVCVO bv);
	
	public int deletePost(String boardNo);
	
	public List<BoardMVCVO> checkPost(BoardMVCVO bv);
	
	public List<BoardMVCVO> searchAll();
	
	public boolean checkNo(String boardNo);

}
