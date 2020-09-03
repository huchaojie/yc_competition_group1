package bean_study2;

/**
 * 秒表： 在运行时累计时间
 * 可以重复启停秒表
 * 可以使用秒表来测量程序的运行时间
 */
public class StopWatch {
	private long elapsedTime;   //所运行的时间
	private long startTime;    //起动的时间点
	private boolean isRunning;  //是否正在运行. 
	
	public StopWatch(){
		reset();
	}
	
	/**
	 * 启动秒表，开始计时
	 */
	public void start(){
		if( isRunning){
			return;
		}
		isRunning=true;
		startTime=System.currentTimeMillis();
	}
	/**
	 * 停止秒表，时间停止累计，并被追加到已流逝的时间中。
	 */
	public void stop(){
		if(  !isRunning){
			return;
		}
		isRunning=false;
		long endTime=System.currentTimeMillis();
		elapsedTime=elapsedTime+endTime-startTime;
	}
	
	/**
	 * 返回总的流逝时间
	 * @return
	 */
	public long getElapsedTime(){
		if( isRunning){
			long endTime=System.currentTimeMillis();
			return elapsedTime+endTime-startTime;
		}else{
			return elapsedTime;
		}
	}
	/**
	 * 重置秒表
	 */
	public void reset(){
		elapsedTime=0;
		isRunning=false;
	}
	
	
}
