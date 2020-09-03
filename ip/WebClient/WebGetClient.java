package WebClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class WebGetClient {

	/**
	 * 本程序是一个http客户端，用来建立一个web主机的连接，服务器地址从args中取得
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		String host;//主机地址
		String resource; //资源名
		
		if(args.length==2) {
			host=args[0];
			resource=args[1];
		}else {
			host="www.baidu.com";
			resource="/index.html";
		}
		
		//打开套接字
		Socket s=new Socket(host,80);
		InputStream inputStream = s.getInputStream();
		OutputStream outputStream = s.getOutputStream();
		
		
		//将流转换为扫描器与写入器
		Scanner in=new Scanner(inputStream); //将字节流转为字符流
		PrintWriter out=new PrintWriter(outputStream);
		
		//发送命令  ->
		String commad="GET "+resource+" HTTP/1.0\n\n";
		out.println(commad);
		out.flush();
		
		//读取服务器响应
		while(in.hasNextLine()) {
			String input=in.nextLine();
			System.out.println(input);
		}
		
	}
}
