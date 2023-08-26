package kr.or.ddit.basic;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelIO {
	
	Scanner sc = new Scanner(System.in);
	private static Map<String, HotelVO> hmap = new HashMap<String, HotelVO>();

	
	public static void main(String[] args) {
		new HotelIO().program();
	}
	
	public void program() {
		
		fileLoad();
		
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
			sc.nextLine();

			switch (s_Choice) {
			case "1":
				checkIn();
				break;

			case "2":
				checkOut();
				break;
			
			case "3":
				condition();
				break;
				
			case "4":
				fileSave();
				System.out.println("감사합니다");
				System.exit(0);
			}
		}
	}
	
	
	public void fileSave() {
		ObjectOutputStream oos = null;
		Set<String> keySet = hmap.keySet();
		
		try {			
			oos = new ObjectOutputStream(new FileOutputStream("e:/D_Other/HotelData.bin"));
			
			for(String num : keySet) {
				HotelVO hv = hmap.get(num);
				oos.writeObject(hv);
					
				System.out.println("파일에 쓰기 작업 완료!!!");
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				oos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void fileLoad(){
		
		ObjectInputStream ois = null;
		
		try {
			
			ois = new ObjectInputStream(new FileInputStream("e:/D_Other/HotelData.bin"));
			
			Object obj = null;
			
			while((obj=ois.readObject())!=null) {
				HotelVO hv = (HotelVO) obj;
				hmap.put(hv.getNum(), hv);
				
			}
			
			
			
		}catch(IOException | ClassNotFoundException ex) {
			System.out.println("읽기 완료.");
		} 
		finally {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			
		}
	}
	

	public void checkIn() {
		HotelIO hi = new HotelIO(); 

		System.out.print("어느방에 체크인 하시겠습니까?\r\n" + 
				"방번호 입력 =>");

		String num = sc.next();
		
		if(hmap.get(num) != null){
			System.out.println("이미 예약된 객실입니다.");
			checkIn();
			return;
		} else {
		
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?\r\n" + 
				"이름 입력 => ");
		
		String name = sc.next();
		
		hmap.put(num, new HotelVO(num, name));

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
	
	
	public void condition() {
		
		Set<String> keySet = hmap.keySet();
		
		Iterator<String> it = keySet.iterator();	// 왜필요함?
		
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key + "호실 : " + hmap.get(key) + "손님");
		}
	}
	

}

class HotelVO implements Serializable {
	private String name;
	private String num;
	
	
	public HotelVO(String name, String num) {
		super();
		this.name = name;
		this.num = num;
	}
	
	public HotelVO() {
		
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	@Override
	public String toString() {
		return "[이름=" + name + ", 객실=" + num + "]";
	}
		
	
	
}
