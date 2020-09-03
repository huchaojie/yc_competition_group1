package download1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Download {

	public static void main(String[] args) throws IOException {
		URL url=new URL("https://qd.myapp.com/myapp/qqteam/pcqq/PCQQ2020.exe");
		String filename=getFileName(url);
		
		//路径的取法：最要不要用绝对路径  使用用户目录
		String userHome=System.getProperty("user.home");
		
		File file=getSavaFile(userHome, filename);
		if(file.exists()) {
			file.delete();
		}
		long filesize=getDownloadFileSize(url);
		createEmptyFile(file, filesize);//根据文件大小 创建空文件
		
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
		BufferedInputStream iis = new BufferedInputStream(url.openStream());
		byte []bs=new byte[1024];
		int length=-1;
		while((length=iis.read(bs,0,bs.length))!=-1) {
			bos.write(bs,0,length);
			bos.flush();
		}
		bos.flush();
		bos.close();
		iis.close();
		
		System.out.println("下载文件成功，保存位置为："+file.getAbsolutePath());
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
