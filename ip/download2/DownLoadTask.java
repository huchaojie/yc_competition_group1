package download2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;


public class DownLoadTask implements Runnable{

	private URL url;
	private File file;
	private int i;
	private long sizeperthread;
	private long start;
	private long end;
	
	private MyNotify mynotify;
	
	public DownLoadTask(URL url, File file, int i, long sizeperthread,MyNotify mynotify) {
		this.url = url;
		this.file = file;
		this.i = i;
		this.mynotify=mynotify;
		this.sizeperthread = sizeperthread;
		this.start = i*this.sizeperthread;
		this.end = (i+1)*this.sizeperthread-1;
	}
	
	
	@Override
	public void run() {
		RandomAccessFile raf=null;
		BufferedInputStream bis=null;
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Range", "bytes=" + start + "-" +end);
			
			raf=new RandomAccessFile(file, "rw");
			raf.seek(start);//设定指针的位置
			bis=new BufferedInputStream(con.getInputStream());
			byte []bs=new byte[500*1024];//每循环一次，最多下载1k
			int length=-1;
			//length：真实下载的大小
			while((length=bis.read(bs,0,bs.length))!=-1) {
				raf.write(bs,0,length);
				if(mynotify!=null) {
					mynotify.notifyResult(length);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(bis!=null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
}
