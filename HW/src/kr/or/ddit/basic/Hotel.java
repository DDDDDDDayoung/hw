package kr.or.ddit.basic;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {
	
	Scanner sc = new Scanner(System.in);
	Map<String, String> hmap = new HashMap<String, String>();


	public static void main(String[] args) {
		// 메소드나 필드에 접근하려면 객체의 생성이 있어야되잖아?
		// Scanner클래스의 nextInt()라던가 이용ㅎ라려면
		// Scanner sc = new Scanner(System.in); 이렇게 new연산자로 객체의 생성을 한다음
		// sc.nextInt(); 이런식으로
		// 근데 static으로 선언된 정적필드나 정적메소드는 객체의 생성 없이 접근이 가능해 뭐로? 클래스명.메소드명 = 접근연산자로
		
		// 근데 static이 객체의 생성없이 접근이 된다고 했자너/
		// 근데예를들어 정적변수 static이 안붙은 age라고 있다하자
		// 근데 age자체에 접근하려면 뭐가만들어져야돼? => 객체
		// static메소드인 getAge()가 있다하자 getAge()는 객체의 생성 필요해 안해 => 안하지
		// 근데 객체의생성을 안하는 메소드에서 객체의 생성이필요한 요소들을 쓸수없는거야
		// 말이안되잖아 말이
		
		//new로 객체를 만들어서 program()을 실행하던가 or program()을 static으로 선언해주던가
		new Hotel().program();
	}
	
	public void program() {

		String s_Choice = "";
		while (true) {
			System.out.print("\r\n" + 
					"**************************\r\n" + 
					"호텔 문을 열었습니다.\r\n" + 
					"**************************\r\n" + 
					"\r\n" + 
					"*******************************************\r\n" + 
					"어떤 업무를 하시겠습니까?\r\n" + 
					"1.체크인  2.체크아웃 3.객실상태 4.업무종료\r\n" + 
					"*******************************************\r\n" + 
					"메뉴선택 =>");

			s_Choice = sc.next();
			System.out.println();

			switch (s_Choice) {
			case "1":
				checkIn();
				break;

			case "2":
				checkOut();
				break;
			
			case "3":
				status();
				break;
				
			case "4":
				System.out.println("감사합니다");
				System.exit(0);
			}
		}
	}

	public void checkIn() {
		

		System.out.print("어느방에 체크인 하시겠습니까?\r\n" + 
				"방번호 입력 =>");

		String num = sc.next();
		
		if(hmap.get(num) != null){
			System.out.println("이미 예약된 객실입니다.");
		} else {
		
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?\r\n" + 
				"이름 입력 => ");
		
		String name = sc.next();
		
		hmap.put(num, name);
		
		System.out.println("체크인 되었습니다.");

		}
	}

		

	
	
	public void checkOut() {
		
		System.out.println("어느방을 체크아웃 하시겠습니까?\r\n" + 
				"방번호 입력 => ");
		
		String num = sc.next();
		
		if(hmap.get(num) != null) {
			hmap.remove(num);
			System.out.println("체크아웃 되었습니다.");
		} else {
			System.out.println("예약이 존재하지 않는 객실입니다.");
		}
	}
	
	
	public void status() {
		
		Set<String> keySet = hmap.keySet();
		
		for(String key : keySet) {
			System.out.println(key + "호실 : " + hmap.get(key) + "손님");
		}
	}
}
