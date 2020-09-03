package Thread;

public class Test1 {

	public static void main(String[] args) {
		DownTown d=new DownTown(10);
		Thread t1=new Thread(d);
		t1.setName("线程一");
		t1.start();
		Thread t2=new Thread(d);
		t2.setName("线程二");
		t2.start();
		Thread t3=new Thread(d);
		t3.setName("线程三");
		t3.start();
		
	}
}


class DownTown implements Runnable{
	private int total; //总票数
	
	public DownTown(int total) {
		this.total = total;
	}

	@Override
	public void run() {
		while(true) {
			synchronized (this) {
				if(total<=0) {//this当前枷锁的对象
					return;
				}
				System.out.println("线程:"+Thread.currentThread().getName()+"正在售卖第"+total+"张票");
				total--;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	}
	
}