package download3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import download3.threadpool.MyNotify;
import download3.threadpool.ThreadPoolManger;


/**
 * 下载管理器
 * @author fangxiang
 *
 */
public class DownLoadManager {

	private int threadSize;
	private URL url;
	private String savePath;
	private long filesize;
	private long sizeperthread;//每个线程要下载文件的大小
	
	private ThreadPoolManger threadPoolManger;//线程池管理器
	private MyNotify mynotify;
	
	
	public DownLoadManager(int threadSize, URL url) {
		this.threadSize = threadSize;
		this.url = url;
	}

	public DownLoadManager(int threadSize, URL url, String savePath ,ThreadPoolManger threadPoolManger,MyNotify mynotify) {
		this.threadSize = threadSize;
		this.url = url;
		this.savePath = savePath;
		this.mynotify=mynotify;
		this.threadPoolManger=threadPoolManger;
	}
	
	public long getFilesize() {
		return filesize;
	}

	public void startDownLoad() {
		try {
			String filename=getFileName(url);
			File file=getSavaFile(this.savePath, filename);
			filesize=getDownloadFileSize(url);
			if(filesize<=0) {
				throw new RuntimeException(
					"download file size should be postive");
			}
			
			createEmptyFile(file, filesize);
			countSizePerthread();
			
			for(int i=0;i<threadSize;i++) {
				//Thread t=new Thread(new DownLoadTask(url, file, i, sizeperthread,mynotify ));
				//t.setDaemon(true);
				//t.start();
				
				this.threadPoolManger.process(new DownLoadTask(url, file, i, sizeperthread, mynotify));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 每个线程要下载文件大小
	 * @return
	 */
	private long countSizePerthread() {
		this.sizeperthread=this.filesize%threadSize==0?this.filesize
				/threadSize :this.filesize/threadSize+1;
		return sizeperthread;
	}
	
	
	/**
	 * 下载前创建空文件   已防止下载过程中不够而产生异常
	 * @param file
	 * @param filesize
	 * @throws IOException
	 */
	private static void createEmptyFile(File file,long filesize) throws IOException {
		RandomAccessFile raf=new RandomAccessFile(file, "rw");//随机访问文件
		raf.setLength(filesize);
		raf.close();
	}
	
	/**
	 * 获取要下载文件的大小  注意通过http的head请求头完成 head请求头只请求响应头  不请求响应体
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private static long getDownloadFileSize(URL url) throws IOException {
		HttpURLConnection con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("HEAD");
		con.connect();
		long length=con.getContentLengthLong();
		return length;
	}
	
	
	/**
	 * 生成按时间的文件名
	 * @param url
	 * @return
	 */
	private static String getFileName(URL url) {
		String extname=url.getPath().substring(url.getPath().lastIndexOf("."));
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		String filename=df.format(new Date());
		return filename+filename;
	}
	
	/**
	 * 指定保存文件的地址
	 * @param dir  地址
	 * @param fileName  文件名
	 * @return
	 */
	private static File getSavaFile(String dir,String fileName) {
		if(dir==null||"".equals(dir.trim())) {
			dir=System.getProperty("user.home");
		}
		File file=new File(dir,fileName);
		return file;
		
	}
}
