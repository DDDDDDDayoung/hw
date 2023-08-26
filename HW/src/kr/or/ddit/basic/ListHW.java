package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ListHW {

   Scanner sc = new Scanner(System.in);

   public static void main(String[] args) {
      new ListHW().init();

   }

   public void init() {

      System.out.println("============================");
      System.out.println("Lotto 프로그램");
      System.out.println("----------------------------");
      System.out.println("1.Lotto구입");
      System.out.println("2.프로그램 종료");
      System.out.println("============================");
      int user = 0;
      System.out.print("번호를 입력하세요 : ");
      user = sc.nextInt();

      if (user == 1) {
         buyLotto();
      } else {
         System.out.println("프로그램이 종료됩니다.");
      }

   }

   public void buyLotto() {

      int money = 0;
      int lottoprice = 1000;
      int change = 0;
      
      
      System.out.println("1000원에 로또번호 하나입니다.");

      System.out.print("금액 입력 : ");
      money = sc.nextInt();

      System.out.println("행운의 로또번호는 아래와 같습니다.");

      Set<Integer> Num = new HashSet<Integer>();

      for (int i = 0; i < money / lottoprice; i++) {
         while (Num.size() < 6) {

            int lottoNum = (int) (Math.random() * 45 + 1);

            Num.add(lottoNum);
         }
         
         System.out.println("로또번호" + (i + 1) + Num);
      }
      
      change = money % lottoprice;
      
      System.out.println("");
      System.out.println("받은 금액은  " + money + "원이고 거스름돈은 " + change + "원입니다." );
      init();

   }

}