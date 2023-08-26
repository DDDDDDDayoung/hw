package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class h0801 {

	public static void main(String[] args) {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			
			 fis = new FileInputStream("e:/D_Other/Tulips.jpg");
			 fos = new FileOutputStream("e:/D_Other/Tulips_copy.jpg");
			 int data = 0;
			 
			 while((data = fis.read()) != -1 ) {
				 fos.write(data);
			 }
			 
			 System.out.println("복사끝");
			 
			 
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
