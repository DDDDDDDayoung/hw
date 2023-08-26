package kr.or.ddit.basic;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelDB {
	Scanner sc = new Scanner(System.in);
	Map<String, String> hotelMap = new HashMap<String, String>();

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public static void main(String[] args) {

		HotelDB hotel = new HotelDB();
		hotel.start();
	}

	private static void printMenu() {

		System.out.println(
				"\r\n" + "**************************\r\n" + "호텔 문을 열었습니다.\r\n" + "**************************\r\n"
						+ "\r\n" + "*******************************************\r\n" + "어떤 업무를 하시겠습니까?\r\n"
						+ "1.체크인  2.체크아웃 3.객실상태 4.업무종료\r\n" + "*******************************************");

		System.out.print("메뉴선택 => ");
	}

	private void start() {

		while (true) {
			
			printMenu();
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomCondition();
				break;
			case 4:
				System.out.println("호텔 문을 닫았습니다.");
				System.exit(0);
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}

		}
	}

	private void checkIn() {
		boolean isExist = false;
		String num = "";
		String name = "";

		do {
			System.out.print("어느방에 체크인 하시겠습니까?\r\n" + "방번호 입력 =>");

			num = sc.next();

			isExist = checkRoom(num);

			if (isExist) {
				System.out.println("이미 예약된 객실입니다.");

			}

		} while (isExist);

		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?\r\n" + "이름 입력 => ");

		name = sc.next();
		sc.nextLine();

		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert INTO hotel_mng (room_num, guest_name) values (?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, num);
			pstmt.setString(2, name);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("체크인 되었습니다.");
			} else {
				System.out.println("체크인에 실패하였습니다.");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}

	}

	private void checkOut() {
		System.out.println("어느방을 체크아웃 하시겠습니까?\r\n" + "방번호 입력 => ");

		String num = sc.next();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql ="delete from hotel_mng where room_num = ?"; 
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, num);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {				
				System.out.println("체크아웃 되었습니다.");
			}else {				
				System.out.println("예약이 존재하지 않는 객실입니다.");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}

	
		

	}

	private void roomCondition() {
		
		try{
			conn = JDBCUtil.getConnection();
			stmt = conn.createStatement();
			
			String sql = "select * from hotel_mng";
			rs = stmt.executeQuery(sql);
			
			System.out.println("------------------------");
			System.out.println(" 객실번호\t\t이름");
			System.out.println("------------------------");
			
			while(rs.next()) {
				String num = rs.getString("room_num");
				String name = rs.getString("guest_name");
				
				System.out.println(" "+num+"\t\t"+name);
			}
			
			System.out.println("------------------------");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		
		

	}

	private boolean checkRoom(String num) {
		boolean isExist = false;

		try {
			conn = JDBCUtil.getConnection();

			String sql = "select count(*) as cnt from hotel_mng where room_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);

			rs = pstmt.executeQuery();

			int cnt = 0;

			while (rs.next()) {
				cnt = rs.getInt("CNT");
			}

			if (cnt > 0) {
				isExist = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}

		return isExist;
	}

}
