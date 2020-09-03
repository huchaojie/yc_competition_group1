package download22;

import java.net.MalformedURLException;
import java.net.URL;

public class Test {

	public static void main(String[] args) throws MalformedURLException {
		URL url=new URL("https://qd.myapp.com/myapp/qqteam/pcqq/PCQQ2020.exe");
		
		DownLoadManager dlm=new DownLoadManager(3, url);
		dlm.startDownLoad();
		
		System.out.println("要下载的文件"+url.getPath()+"大小"+dlm.getFilesize());
	}
}
