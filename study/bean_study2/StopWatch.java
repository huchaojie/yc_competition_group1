package bean_study2;

/**
 * ��� ������ʱ�ۼ�ʱ��
 * �����ظ���ͣ���
 * ����ʹ��������������������ʱ��
 */
public class StopWatch {
	private long elapsedTime;   //�����е�ʱ��
	private long startTime;    //�𶯵�ʱ���
	private boolean isRunning;  //�Ƿ���������. 
	
	public StopWatch(){
		reset();
	}
	
	/**
	 * ���������ʼ��ʱ
	 */
	public void start(){
		if( isRunning){
			return;
		}
		isRunning=true;
		startTime=System.currentTimeMillis();
	}
	/**
	 * ֹͣ���ʱ��ֹͣ�ۼƣ�����׷�ӵ������ŵ�ʱ���С�
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
	 * �����ܵ�����ʱ��
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
	 * �������
	 */
	public void reset(){
		elapsedTime=0;
		isRunning=false;
	}
	
	
}
