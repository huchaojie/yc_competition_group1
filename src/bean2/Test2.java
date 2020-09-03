package bean2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test2 {

	public static void main(String[] args) throws InterruptedException {
		//匿名内部类方案
		Thread t=new Thread() {
			public void run() {
				while(true) {
					Date d=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
					System.out.println("当前时间为:"+sdf.format(d));
				
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				}
			}
		};
		t.start();
		
		while(true) {
			System.out.println("游戏正在运行...");
			Thread.sleep(100);
		}
	}

}