package kr.or.ddit.basic;
/*위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 */

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;


public class BoardJDBC {
	Scanner sc = new Scanner(System.in);
	
	private Connection conn;
	private java.sql.Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public static void main(String[] args) {
		new BoardJDBC().start();
	}
	
	public void start() {
		
		while(true) {
			displayMenu();
			int choice = sc.nextInt();
			for(int i=1; i<=5; i++) {
				System.out.println();
			}
			switch(choice) {
			case 1:
				printAll();
				break;
			case 2:
				write();
				break;
			case 3:
				modify();
				break;
			case 4:
				delete();
			case 5:
				search();
				break;
			case 6:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}	
		}
	}

	private void displayMenu() {

			System.out.println("  ╔═══════════════════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════════════════╗");
			System.out.println();
			System.out.println("      .______     ______        ___      .______       _______  \r\n" + 
							   "      |   _  \\   /  __  \\      /   \\     |   _  \\     |       \\ \r\n" + 
							   "      |  |_)  | |  |  |  |    /  ^  \\    |  |_)  |    |  .--.  |\r\n" + 
							   "      |   _  <  |  |  |  |   /  /_\\  \\   |      /     |  |  |  |\r\n" + 
							   "      |  |_)  | |  `--'  |  /  _____  \\  |  |\\  \\----.|  '--'  |\r\n" + 
							   "      |______/   \\______/  /__/     \\__\\ | _| `._____||_______/ \r\n" + 
							   "	  							                                                          "	);
			System.out.println();
			System.out.println("  ╚═══════════════════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════════════════╝");
			System.out.println();
			System.out.println(); 
			System.out.println("  1.전체보기        2.새 글 작성         3.게시글 수정         4.게시글 삭제        5.게시글 검색");
			System.out.println();
			System.out.print("  ━━☞  ");
			
		
	}
	
	
	private void printAll() {
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from jdbc_board";
			rs = stmt.executeQuery(sql);
			System.out.println("  ════════════════════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════════════════");
			System.out.println("  No\t   작성일자\t\t작성자\t\t제목\t게시글 내용");
			System.out.println("  ═════════════════════════════════════════════════════════════════");
			
			while(rs.next()) {
				int postNo = rs.getInt("board_no");
				String tit = rs.getString("board_title");
				String wri = rs.getString("board_writer");
				Date bdate = rs.getDate("board_date");
				String cont = rs.getString("board_content");
				
				System.out.println("  "+postNo + "\t"+ bdate + "\t" + wri + "\t\t" + tit + "\t" + cont);
				
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
		
	}

	private void write() {
		String wri = "";
		String tit = "";
		String cont = "";
		
		System.out.print("  제목 : ");
		tit = sc.next();
		sc.nextLine();
		System.out.print("  작성자: ");
		wri = sc.next();
		sc.nextLine();
		System.out.println();
		System.out.print("  내용>> ");
		cont = sc.nextLine();
		
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
			
			pstmt.setString(1, tit);
			pstmt.setString(2, wri);
			pstmt.setString(3, cont);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("  게시글 작성 완료!");
				for(int i=1; i<=10; i++) {
					System.out.println();
				}
			}else {
				System.out.println("  게시글 작성 실패");
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void modify() {
		boolean isExist = false;
		int postNo = 0;
		
		do {
			System.out.println();
			System.out.print("  수정할 게시글 번호를 입력하세요 ━━☞  ");
			postNo = sc.nextInt();
			
			isExist = checkNo(postNo);
			
			if(!isExist) {
				System.out.println("  존재하지 않는 게시글 번호입니다. 다시 입력해주세요.");
			}
			
		}while(!isExist);
		
		System.out.print("  제목 : ");
		String tit = sc.next();
		sc.nextLine();
		System.out.println();
		System.out.print("  내용>> ");
		String cont = sc.next();
		sc.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "update jdbc_board "
					+ "set board_title=? ,"
					+ "board_content=? "
					+ "where board_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tit);
			pstmt.setString(2, cont);
			pstmt.setInt(3, postNo);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("  게시글이 정상적으로 수정되었습니다.");
				
				for(int i=1; i<=10; i++) {
					System.out.println();
				}
			}else {
				System.out.println("  게시글 수정에 실패하였습니다.");
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}

	private void delete() {
		boolean isExist = false;
		int postNo = 0;
		
		do {
			System.out.println();
			System.out.print("  삭제할 게시글 번호를 입력하세요 ━━☞  ");
			postNo = sc.nextInt();
			
			isExist = checkNo(postNo);
			
			if(!isExist) {
				System.out.println("  존재하지 않는 게시글 번호입니다. 다시 입력해주세요.");
			}
			
		}while(!isExist);
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from jdbc_board where board_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("  게시글이 정상적으로 삭제되었습니다.");
				
				for(int i=1; i<=10; i++) {
					System.out.println();
				}
			}else {
				System.out.println("  게시글 삭제에 실패하였습니다.");
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}

	public void search() {
			
		try {
			
			System.out.println();
			System.out.println("  검색할 정보를 입력하세요  \n");
			
			System.out.println("  게시글번호 ━☞");
			String postNo = sc.nextLine().trim();
			sc.nextLine();
			System.out.println("  게시글제목 ━☞");
			String tit = sc.nextLine().trim();
			sc.nextLine();
			System.out.println("  작성자 ━☞");
			String wri = sc.nextLine().trim();
			sc.nextLine();
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from jdbc_board where 1=1 ";
			
			if(postNo!=null && !postNo.equals("")) {
				sql += "and board_no = ? ";
			}else if(tit != null && !tit.equals("")) {
				sql += "and board_title = ? ";
			}else if(wri != null && !wri.equals("")) {
				sql += "and board_writer = ? ";
			}

			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(postNo!=null && !postNo.equals("")) {
				pstmt.setString(index++, postNo);
			}else if(tit != null && !tit.equals("")) {
				pstmt.setString(index++, tit);
			}else if(wri != null && !wri.equals("")) {
				pstmt.setString(index++, wri);
			}
			
			rs = pstmt.executeQuery();
			
			System.out.println("  ════════════════════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════════════════");
			System.out.println("  No\t   작성일자\t\t작성자\t\t제목\t게시글 내용");
			System.out.println("  ═════════════════════════════════════════════════════════════════");
			
			while(rs.next()) {
				postNo = rs.getString("board_no");
				tit = rs.getString("board_title");
				wri = rs.getString("board_writer");
				Date bdate = rs.getDate("board_date");
				String cont = rs.getString("board_content");
				
				System.out.println("  "+postNo + "\t"+ bdate + "\t" + wri + "\t\t" + tit + "\t" + cont);
				for(int i=1; i<=5; i++) {
					System.out.println();
				}
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}

	private boolean checkNo(int postNo) {
		boolean isExist = false;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			
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
