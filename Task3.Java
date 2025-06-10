package Task3;

import sun.jvm.hotspot.runtime.Threads;

class Data{
	int A1, B1:
	boolean gotoB2 = false;
}

class Utility{
	
	public static int calculateSum(int n) {
	return n*(n+1)/2;
	}
}

class ThreadA extends Threads{
	 
	private Data resource;
	
	public ThreadA(Data resouce) {
		super();
		this.resource = resource;
	}
	
	public void run() {
		
		synchronized(resource) {
			resource.A1 = Utility.calculateSum(500);
			System.out.println("Completed A1 = "+ resource.A1);
			resource.gotoB2 = true;
			resource.notify();
		}
		
		synchronized(resource) {
			
			while(resource.gotoA2 == false) {
				resource.wait();
				System.out.println("Im in A2 waiting for B2 to be done");
				
			}
			resource.A2 = Utility.calulateSum(300) + resource.gotoB2;
			System.out.println("This is my A2"+ A2);
		}
	}
	
}

public class Question3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num_iterations = 6;
		Data my_data = new Data();

	}

}
