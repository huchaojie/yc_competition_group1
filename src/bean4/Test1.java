package bean4;

public class Test1 {

	public static void main(String[] args) {
		DownLoad t=new DownLoad(10);
		Thread t1=new Thread(t);
		t1.setName("窗口一");
		t1.start();
		Thread t2=new Thread(t);
		t2.setName("窗口二");
		t2.start();
		Thread t3=new Thread(t);
		t3.setName("窗口三");
		t3.start();
		
		
	}

}


class DownLoad implements Runnable{
	private int total;//总票数
	
	public DownLoad(int total) {
		this.total = total;
	}

	@Override
	public void run() {
			while(true) {
				synchronized (this) {
				if(total<=0) {
					return;
				}else {
					System.out.println("线程"+Thread.currentThread().getName()+"正在读第"+total+"张票");
					total--;
				}
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
