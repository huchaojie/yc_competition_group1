package download3.threadpool;

public class SimpleThread extends Thread {
	
	private boolean runningFlag;//运行的状态 false
	private Taskable task;//要执行的操作
	private int threadNumber;//线程的编号
	private MyNotify myNotify;//通知接口
	
	//标志runningFlag用于控制线程运行状态
	public boolean isRunning() {
		return this.runningFlag;
	}
	
	//设置线程的运行状态
	public synchronized void setRunning(boolean flag) {
		this.runningFlag=flag;//设置当前线程为true，表示当前线程已经被占用
		if(flag) {
			this.notifyAll();//换洗其他线程就绪
		}
	}
	
	//初始化当前线程  提示哪个线程工作
	public SimpleThread(int threadNumber,MyNotify notify) {
		runningFlag=false;
		this.threadNumber=threadNumber;
		System.out.println("Thread "+threadNumber+" started");
		this.myNotify=notify;
	}
	
	public synchronized void run() {
		try {
			while(true) {
				if(!runningFlag) { //无限循环
					this.wait();//判断标志位是否为true,如果是flase 则进入等待序列
				}else {
					System.out.println("线程 "+threadNumber+" 正在执行任务");
					
					Object returnValue=this.task.doTask();
					
					if(myNotify!=null) {
						myNotify.notifyResult(returnValue);
					}
					
					System.out.println("线程 "+threadNumber+" 任务运行完毕 重新准备");
					setRunning(false);
				}
			}
		} catch (Exception e) {
			System.out.println("Interrupt");
		}
	}

	public Taskable getTask() {
		return task;
	}

	public void setTask(Taskable task) {
		this.task = task;
	}

}
