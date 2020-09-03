package download3.threadpool;

//通知接口：用于通知主线程，当前线程中的任务执行的情况
public interface MyNotify {

	public void notifyResult(Object result);
}
