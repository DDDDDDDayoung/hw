package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
 10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse2라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수를 기준으로 정렬하여 출력한다.
 */
public class h0726 {
	
	static int CURR_RANK = 1;
	
	public static List<Horse2> horList;
	
	public static void main(String[] args) {
		
		horList = 
				new ArrayList<Horse2>();
		horList.add(new Horse2("1번 말"));
		horList.add(new Horse2("2번 말"));
		horList.add(new Horse2("3번 말"));
		horList.add(new Horse2("4번 말"));
		horList.add(new Horse2("5번 말"));
		horList.add(new Horse2("6번 말"));
		horList.add(new Horse2("7번 말"));
		horList.add(new Horse2("8번 말"));
		horList.add(new Horse2("9번 말"));
		horList.add(new Horse2("10번 말"));
		
		Thread hpd = new Horse2PositionDisplay();
		hpd.start();
		
		for(int i = 0; i<horList.size(); i++) {
			horList.get(i).start();
		}
		
		try {
			hpd.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("=====================================");
		System.out.println("경마가 종료되었습니다!");
		System.out.println();
		
		Collections.sort(horList);
		
		System.out.println("=====================================");
		System.out.println("              경마 순위");
		System.out.println("=====================================");
		for(int i=0; i<horList.size(); i++) {
			System.out.println(horList.get(i).getRank()+"등 : "+horList.get(i).getHorse2Name());
		}
		
		
	
		
	}

}


class Horse2 extends Thread implements Comparable<Horse2>{
	private String name;
	private int rank;
	private int position;
	
	public Horse2(String name) {
		super();
		this.name = name;
	}
	

	public String getHorse2Name() {
		return name;
	}



	public void setHorse2Name(String name) {
		this.name = name;
	}



	public int getRank() {
		return rank;
	}



	public void setRank(int rank) {
		this.rank = rank;
	}



	public int getPosition() {
		return position;
	}



	public void setPosition(int position) {
		this.position = position;
	}



	@Override
	public void run() {
			
		for(int i=0; i<50; i++) {			
			
			try {
				Thread.sleep((int)(Math.random()*500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setPosition(i);
		}
		this.rank = h0726.CURR_RANK;
		setRank(h0726.CURR_RANK++);
	}


	@Override
	public int compareTo(Horse2 o) {

		return new Integer(this.getRank()).compareTo(o.getRank());
	}
	
	
}

class Horse2PositionDisplay extends Thread{
	
	@Override
	public void run() {
		
		System.out.println("==========================================================");
		System.out.println(" 경마가 시작되었습니다!");
		while(true) {
			for(int i=1; i<=5; i++) {
				System.out.println();
			}
		System.out.println("==========================================================");
		int finishedH = 0;
		
		for(int i=0; i<h0726.horList.size(); i++) {
			String Course = "--------------------------------------------------";
			Horse2 Horse2 = h0726.horList.get(i);
			
			if(Horse2.getPosition() != 49) {
				System.out.print(Horse2.getHorse2Name() + " : ");
				System.out.print(Course.substring(0, Horse2.getPosition()) + ">");
				System.out.println(Course.substring(Horse2.getPosition()+1, 50));
			}else {
				System.out.print(Horse2.getHorse2Name() + " : ");
				System.out.print(Course+"도착");
				System.out.println();
				
				finishedH++;
			}
			
		}
		System.out.println("==========================================================");
	
		if(finishedH == 10) {
			return;
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
	}
	
}


