package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class BoardMVCdaoImpl implements BoardMVCdao{
	Scanner sc = new Scanner(System.in);
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	
	@Override
	public int insertPost(BoardMVCVO bv) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into jdbc_board ("
				+ "board_no, "
				+ "board_title, "
				+ "board_writer,"
				+ "board_date,"
				+ "board_content)"
				+" VALUES (board_seq.nextval, ?, ?, sysdate, ?)";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardWriter());
			pstmt.setString(3, bv.getBoardContent());
		
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	
	@Override
	public int updatePost(BoardMVCVO bv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "update jdbc_board"
					+ " set board_title = ?,"
					+ " board_content = ?"
					+ " where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardContent());
			pstmt.setString(3, bv.getBoardNo());
			
			
			cnt = pstmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	
	@Override
	public int deletePost(String boardNo) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from jdbc_board where board_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
			cnt = pstmt.executeUpdate();	
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}
	

	@Override
	public List<BoardMVCVO> checkPost(BoardMVCVO bv) {
		
		List<BoardMVCVO> borList = new ArrayList<BoardMVCVO>();
		
		try {	
			sc.nextLine();
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from jdbc_board where 1=1 ";
			
			if(bv.getBoardNo() !=null && !bv.getBoardNo().equals("")) {
				sql += "and board_no = ? ";
			}else if(bv.getBoardTitle() != null && !bv.getBoardTitle().equals("")) {
				sql += "and board_title = ? ";
			}else if(bv.getBoardWriter() != null && !bv.getBoardWriter().equals("")) {
				sql += "and board_writer = ? ";
			}

			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(bv.getBoardNo() !=null && !bv.getBoardNo().equals("")) {
				pstmt.setString(index++, bv.getBoardNo());
			}else if(bv.getBoardTitle() != null && !bv.getBoardTitle().equals("")) {
				pstmt.setString(index++, bv.getBoardTitle());
			}else if(bv.getBoardWriter() != null && !bv.getBoardWriter().equals("")) {
				pstmt.setString(index++, bv.getBoardWriter());
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String boardNo = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				Date boardDate = rs.getTimestamp("board_date");
				String boardContent = rs.getString("board_content");
				
				BoardMVCVO bv2 = new BoardMVCVO(boardNo, boardTitle, boardWriter, boardDate, boardContent);
				
				borList.add(bv2);
			}
			
				
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return borList;
		
	}

	
	@Override
	public List<BoardMVCVO> searchAll() {
		
		List<BoardMVCVO> borList = new ArrayList<BoardMVCVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from jdbc_board";
			rs = stmt.executeQuery(sql);
			
			
			while (rs.next()) {
				String boardNo = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				Date boardDate = rs.getTimestamp("board_date");
				String boardContent = rs.getString("board_content");
				
				BoardMVCVO bv2 = new BoardMVCVO(boardNo, boardTitle, boardWriter, boardDate, boardContent);
				
				borList.add(bv2);
			}
			
			System.out.println("  ═════════════════════════════════════════════════════════════════");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return borList;
	}

	
	@Override
	public boolean checkNo(String boardNo) {
	boolean isExist = false;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) {
				cnt=rs.getInt("CNT");
			}
			
			if(cnt > 0) {
				isExist = true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.getConnection();
		}
		
		return isExist;
	}

}
