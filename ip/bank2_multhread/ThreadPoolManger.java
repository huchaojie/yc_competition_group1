package bank2_multhread;

import java.util.Vector;

public class ThreadPoolManger {

	private int initThreads;//线程池中初始线程的数量
	private Vector vector;//Vector 类可以实现可增长对象数组  用于存多个线程
	private MyNotify notify;
	
	public void setInitThreads(int initThreads) {
		this.initThreads=initThreads;
	}
	
	public ThreadPoolManger(int initThreads,MyNotify notify) {
		this.notify=notify;
		this.setInitThreads(initThreads);
		System.out.println("线程池开始运行了...");
		vector=new Vector();
		for(int i=1;i<=initThreads;i++) {
			SimpleThread thread = new SimpleThread(i, this.notify);
			vector.addElement(thread);//将制定的组件添加到此向量的末尾  将其大小增加1
			thread.start();
		}
	}
	
	public void process(Taskable task) {
		int i;
		for(i=0;i<vector.size();i++) {
			SimpleThread currentThread=(SimpleThread) vector.elementAt(i);//返回指定索引的组件
			if(!currentThread.isRunning()) {
				System.out.println("Thread"+(i+1)+"开始执行任务了...");
				currentThread.setTask(task);
				currentThread.setRunning(true);
				return;
			}
		}
		System.out.println("================");
		System.out.println("线程池中没有空闲的线程,扩容...");
		System.out.println("================");
		if(i>=vector.size()) {
			int temp = vector.size();
			for(int j=temp+1;j<=temp+10;j++) {
				SimpleThread thread = new SimpleThread(j, this.notify);
				vector.addElement(thread);
				thread.start();
			}
			//创建玩之后需要重新执行process
			this.process(task);//很重要
		}
		
	}
	
}
