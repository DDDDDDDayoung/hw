package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Task {

	public static void main(String[] args) {
		
		List<Student> stuList = new ArrayList<Student>();
		
		stuList.add(new Student("10144", "홍길동", 94, 85, 37)); //216   4
		stuList.add(new Student("10147", "변학도", 65, 83, 78)); //226   3
		stuList.add(new Student("10134", "성춘향", 53, 92, 60)); //205   5
		stuList.add(new Student("10151", "이순신", 84, 93, 73)); //250   2
		stuList.add(new Student("10137", "강감찬", 83, 94, 75)); //252   1
		stuList.add(new Student("10142", "일지매", 88, 63, 41)); //192   6
		stuList.add(new Student("10143", "오다영", 88, 89, 75)); //252   1
		
		Task ta = new Task();
		ta.setRanking(stuList);
		
		System.out.println("정렬 전 : ");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		System.out.println("====================================");
		
	
		
		Desc1 desc= new Desc1();
		Collections.sort(stuList);
		Collections.sort(stuList, desc);
		
		
		System.out.println("정렬 후 : ");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		
		
	}
	
	public void setRanking(List<Student> stuList){
		for(Student stu : stuList){
			int rank = 1;
			for(Student stu2 : stuList){
				if(stu.getSum() < stu2.getSum()){
					rank++;
				}					
			}
			stu.setRank(rank);
		}
		
	}

}

class Student implements Comparable<Student>{
	private String num;
	private String name;
	private int kor;	
	private int eng;
	private int mat;
	private int sum;
	private int rank;
	
	public Student(String num, String name, int kor, int eng, int mat) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.sum = kor+mat+eng;
		
	}
	
	

	

	public String getNum() {
		return num;
	}





	public void setNum(String num) {
		this.num = num;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public int getKor() {
		return kor;
	}





	public void setKor(int kor) {
		this.kor = kor;
	}





	public int getEng() {
		return eng;
	}





	public void setEng(int eng) {
		this.eng = eng;
	}





	public int getMat() {
		return mat;
	}





	public void setMat(int mat) {
		this.mat = mat;
	}





	public int getSum() {
		return sum;
	}





	public void setSum(int sum) {
		this.sum = sum;
	}





	public int getRank() {
		return rank;
	}





	public void setRank(int rank) {
		this.rank = rank;
	}





	

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", sum="
				+ sum + ", rank=" + rank + "]";
	}





	@Override
	public int compareTo(Student stu) {
		
		return this.getNum().compareTo(stu.getNum());
	}

	

}


class Desc1 implements Comparator<Student> {
	
	@Override
	public int compare(Student s1, Student s2) {
		Integer a= s1.getSum();
		Integer b= s2.getSum();
		
		if(a==b) {
			return s1.getNum().compareTo(s2.getNum());
		}
		
		return a.compareTo(b) * -1;
	}
	
}