package kr.or.ddit.basic;

import java.text.SimpleDateFormat;
import java.util.Date;

class BoardMVCVO {
	
	private String boardNo;
	private String boardTitle;
	private String boardWriter;
	private Date boardDate;
	private String boardContent;
	
	
	
	
	public BoardMVCVO(String boardTitle, String boardContent) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
	}

	public BoardMVCVO(String boardNo, String boardTitle, String boardWriter, Date boardDate, String boardContent) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardDate = boardDate;
		this.boardContent = boardContent;
	}

	public BoardMVCVO(String boardTitle, String boardWriter,String boardContent) {
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
	}
	
	public BoardMVCVO() {
		
	}

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	
	public String getboardDateDisplay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		return sdf.format(this.boardDate);
	}

	@Override
	public String toString() {
		return "BoardMVCVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardDate=" + boardDate + ", boardContent=" + boardContent + "]";
	}

}
