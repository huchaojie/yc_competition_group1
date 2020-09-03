package WebClient;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * 通过一个url对象的openConnection方法可以获取一个URLConnection对象
 * 使用这个URLConnection对象可以向指定的服务器发送请求，也可以获取到服务器的响应
 * 注意：URLConnection是与协议无关的类
 * @param args
 * @throws Exception 
 */
public class HttpURLConnectionTest {

	public static void main(String[] args) throws Exception {
		URL url=new URL("http://www.baidu.com:80/index.html");
		//使用url的openConnection方法来获取URLConnection对象
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		System.out.println("**********使用HttpURLConnection增强了对http协议的访问功能***********");
		System.out.println("请求方式"+con.getRequestMethod());
		System.out.println("响应状态码"+con.getResponseCode());
		System.out.println("响应结果"+con.getResponseMessage());
		
		//URLConnection也可以向服务器输出流，但要通过doOutput()方法打开输出许可
				//下面的测试报501错误
				//con.setDoOutput(true);
//				OutputStream oos = con.getOutputStream();
//				PrintWriter pw = new PrintWriter(oos);
//				pw.write("hello");
//				pw.flush();
				
				//获取输入流
				InputStream iis = con.getInputStream();
				Scanner sc=new Scanner(iis); //将字节转为字符
				//获取资源信息
				System.out.println("信息内容如下"); //用了urlconnection之后，就只有响应体了
				while(sc.hasNext()) {
					System.out.println(sc.nextLine());
				}
				
				
				//URLConnection对象还可以获取资源文件的类型
				System.out.println("文件类型"+con.getContentType());
				//获取资源文件长度
				System.out.println("文件长度"+con.getContentLength());
				//获取资源文件创建时间
				System.out.println("创建时间"+con.getDate());
				
				iis.close();
	}
}
