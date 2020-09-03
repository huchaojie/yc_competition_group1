package download2;

import java.net.MalformedURLException;
import java.net.URL;

public class Test {
	private static Object o=new Object();
	static DownLoadManager dlm;
	static long total;

	public static void main(String[] args) throws MalformedURLException {
		URL url=new URL("https://qd.myapp.com/myapp/qqteam/pcqq/PCQQ2020.exe");
		
		 dlm=new DownLoadManager(3, url,"",new MyNotify() {
			@Override
			public void notifyResult(long downsize){
				synchronized (o) {
					total+=downsize;//累加这个大小
				}
				System.out.println("当前下载了数据量："+total+",百分比"+((double)total/dlm.getFilesize()));
			}
		});
		dlm.startDownLoad();
		System.out.println("要下载的文件"+url.getPath()+"大小"+dlm.getFilesize());
	}
}
	//回调通知的接口
	interface MyNotify{
		public void notifyResult(long length);
	}

