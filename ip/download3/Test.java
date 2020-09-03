package download3;

import java.net.MalformedURLException;
import java.net.URL;

import download3.threadpool.MyNotify;
import download3.threadpool.ThreadPoolManger;

public class Test {
	private static Object o=new Object();
	static DownLoadManager dlm;
	static long total;

	public static void main(String[] args) throws MalformedURLException {
		URL url=new URL("https://qd.myapp.com/myapp/qqteam/pcqq/PCQQ2020.exe");
		
		ThreadPoolManger tpm=new ThreadPoolManger(10, null); //线程池启动
		
		 dlm=new DownLoadManager(3, url,"",tpm, new MyNotify() {
			@Override
			public void notifyResult(Object result){
				synchronized (o) {
					total+=(Long.parseLong(result.toString()));//累加这个大小
				}
				System.out.println("当前下载了数据量："+total+",百分比"+((double)total/dlm.getFilesize()));
			}
		
		});
		dlm.startDownLoad();
		System.out.println("要下载的文件"+url.getPath()+"大小"+dlm.getFilesize());
	}
}

