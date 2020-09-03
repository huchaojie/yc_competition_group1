package bean1;

import java.util.Date;

public class Test1 {

	public static void main(String[] args) {
		//线程的启动
		ShowTime s=new ShowTime();
		s.start();
		
//		for(int i=0;i<100;i++) {
//			System.out.println("主线程"+i);
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		System.out.println("主程序执行完了...");
	}
	
}
//创建一个线程类
	class ShowTime extends Thread{
		public void run() {
		for(int i=0;i<100;i++) {
			System.out.println(i+" "+new Date());
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		}
	}