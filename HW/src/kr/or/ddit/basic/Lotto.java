package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Lotto {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String s_Choice = "";
		while (!("2".equals(s_Choice))) {
			System.out.print("	==================================\r\n" + "         Lotto 프로그램\r\n"
					+ "	----------------------------------\r\n" + "	 1. Lotto 구입\r\n" + "	 2. 프로그램 종료\r\n"
					+ "	==================================		 \r\n" + "	메뉴선택 :");

			s_Choice = sc.next();
			System.out.println();

			switch (s_Choice) {
			case "1":
				buy(sc);
				break;

			case "2":
				end(sc);
				break;
			}
		}
	}

	public static void buy(Scanner sc) {

		Set<Integer> intRnd = new HashSet<Integer>();

		System.out.print(" 	Lotto 구입 시작\r\n" + "		 \r\n" + "	(1000원에 로또번호 하나입니다.)\r\n" + "	금액 입력 :");

		int money = sc.nextInt();

		System.out.println();
		System.out.println("	행운의 로또번호는 아래와 같습니다.");

		for (int i = 0; i < money / 1000; i++) {

			intRnd.clear();
			while (intRnd.size() < 6) {

				int num = (int) (Math.random() * 45 + 1);
				intRnd.add(num);

			}

			System.out.println("	로또번호" + (i + 1) + ": " + intRnd);

		}

		int changeMoney = money % 1000;
		System.out.println();
		System.out.println("	받은 금액은 " + money + "윈이고 거스름돈은 " + changeMoney + "윈입니다.");
		System.out.println();

	}

	public static void end(Scanner sc) {
		System.out.println();
		System.out.println("	감사합니다");
	}

}
