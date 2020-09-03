package bean2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {

	public static void main(String[] args) throws InterruptedException {
		//多态  父类的引用指向子类对象
		Thread c=new Clock();
		c.start();//当调用thread中start方法时  jvm系统会自动调用  Thread中被重写的run()
		
		while(true) {
			System.out.println("游戏正在运行...");
			Thread.sleep(1000);
		}
	}
	
}	
	//写一个外部类
	//写一个类继承Thread 在Thread中显示时间(每秒显示)
	class Clock extends Thread{
		public void run() {
			while(true) {
				Date d=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
				System.out.println("当前时间为:"+sdf.format(d));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

