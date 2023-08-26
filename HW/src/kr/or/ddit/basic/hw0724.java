package kr.or.ddit.basic;


public class hw0724 {
	
	public static void main(String[] args) {
		
		new hw0724().calculate();
		
	}
	
	public enum Planet{수성(2439), 금성(6052), 지구(6371), 화성(3390), 
						목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
			
		private int data;
		
		Planet(int data){
			this.data = data;
		}
		
		public int getData() {
			return data;
		}
	}
	
	
	public void calculate() {
		
		Planet[] planetArr = Planet.values();
		
		
		for(int i=0; i<planetArr.length; i++) {
			double vol = Math.pow(4*3.14*(planetArr[i].getData()), 2);
			
			System.out.println(planetArr[i].name() + " : "
					+ vol);
		}
		
		

	}
	
}