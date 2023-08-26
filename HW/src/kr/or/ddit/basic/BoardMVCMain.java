package kr.or.ddit.basic;
/*위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class BoardMVCMain {
	Scanner sc = new Scanner(System.in);
	private BoardMVCService borService;
	
	public BoardMVCMain() {
		borService = new BoardMVCServiceImpl();
	}
	
	public static void main(String[] args) {
		new BoardMVCMain().start();
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
				break;
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
		
		
		List<BoardMVCVO> borList = borService.viewAll();
		
		System.out.println("  ════════════════════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════════════════");
		System.out.println("  No\t   작성일자\t\t작성자\t\t제목\t게시글 내용");
		System.out.println("  ═════════════════════════════════════════════════════════════════");
		if(borList.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다.");
		}else {
			
			for(BoardMVCVO bv : borList) {
			System.out.println("  "+bv.getBoardNo() + "\t"+ bv.getboardDateDisplay() + "\t" 
					+ bv.getBoardWriter() + "\t\t" + bv.getBoardTitle() + "\t" + bv.getBoardContent());
			}
		}
		
		for(int i=1; i<=5; i++) {
			System.out.println();
		}
		
	}

	private void write() {
		String boardWriter = "";
		String boardTitle = "";
		String boardContent = "";
		
		System.out.print("  제목 : ");
		boardTitle = sc.next();
		sc.nextLine();
		System.out.print("  작성자: ");
		boardWriter = sc.next();
		sc.nextLine();
		System.out.println();
		System.out.print("  내용>> ");
		boardContent = sc.nextLine();
		
		BoardMVCVO bv = new BoardMVCVO(boardTitle, boardWriter, boardContent);
		
		int cnt = borService.registerPost(bv);
			
			if(cnt>0) {
				System.out.println("  게시글 작성 완료!");
				for(int i=1; i<=10; i++) {
					System.out.println();
				}
			}else {
				System.out.println("  게시글 작성 실패");
			}
	}
	

	private void modify() {
		boolean isExist = false;
		String boardNo = "";
		
		do {
			System.out.println();
			System.out.print("  수정할 게시글 번호를 입력하세요 ━━☞  ");
			boardNo = sc.next();
			
			isExist = borService.checkPost(boardNo);
			
			if(!isExist) {
				System.out.println("  존재하지 않는 게시글 번호입니다. 다시 입력해주세요.");
			}
			
		}while(!isExist);
		
		System.out.print("  제목 : ");
		String boardTitle = sc.next();
		sc.nextLine();
		System.out.println();
		System.out.print("  내용>> ");
		String boardContent = sc.next();
		sc.nextLine();
		
		BoardMVCVO bv = new BoardMVCVO(boardTitle, boardContent);
		bv.setBoardNo(boardNo);
		
		int cnt = borService.modifyPost(bv);
		
		if(cnt>0) {
			System.out.println("  게시글이 정상적으로 수정되었습니다.");
			
			for(int i=1; i<=10; i++) {
				System.out.println();
			}
		}else {
			System.out.println("  게시글 수정에 실패하였습니다.");
		}
		
	}
	

	private void delete() {
		boolean isExist = false;
		String boardNo = "";
		
		do {
			System.out.println();
			System.out.print("  삭제할 게시글 번호를 입력하세요 ━━☞  ");
			boardNo = sc.next();
			
			isExist = borService.checkPost(boardNo);
			
			if(!isExist) {
				System.out.println("  존재하지 않는 게시글 번호입니다. 다시 입력해주세요.");
			}
			
		}while(!isExist);
		
		int cnt = borService.removePost(boardNo);
		
		if(cnt>0) {
			System.out.println("  게시글이 정상적으로 삭제되었습니다.");
			
			for(int i=1; i<=10; i++) {
				System.out.println();
			}
		}else {
			System.out.println("  게시글 삭제에 실패하였습니다.");
		}
		
		
		
	}

	public void search() {
		sc.nextLine();
		System.out.println();
		System.out.println("  검색할 정보를 입력하세요  \n");
		
		System.out.println("  게시글번호 ━☞");
		String boardNo = sc.nextLine().trim();
		
		System.out.println("  게시글제목 ━☞");
		String boardTitle = sc.nextLine().trim();
		
		System.out.println("  작성자 ━☞");
		String boardWriter = sc.nextLine().trim();
		
		BoardMVCVO bv = new BoardMVCVO();
		bv.setBoardNo(boardNo);
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		
		
		List<BoardMVCVO> borList = borService.searchPost(bv);
		
		
		System.out.println("  ════════════════════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════════════════");
		System.out.println("  No\t   작성일자\t\t작성자\t\t제목\t게시글 내용");
		System.out.println("  ═════════════════════════════════════════════════════════════════");
		
		if(borList.size() == 0) {
			System.out.println("해당 게시글이 존재하지 않습니다.");
		}else {
			
			for(BoardMVCVO bv2 : borList) {
			System.out.println("  "+bv2.getBoardNo() + "\t"+ bv2.getboardDateDisplay() + "\t" 
					+ bv2.getBoardWriter() + "\t\t" + bv2.getBoardTitle() + "\t" + bv2.getBoardContent());
			}
			
			for(int i=1; i<=5; i++) {
				System.out.println();
			}
		}
		
	}

}
