package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client3 {

	public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException {
		Socket s;
		if(args.length==2) {
			 s=new Socket(args[0],Integer.parseInt(args[1]));
		}else {
			 s=new Socket("localhost",9995);
		}
		System.out.println("客户端"+s.getLocalAddress()+"连接服务器"+s.getRemoteSocketAddress());
		//将字节流转为字符流
		InputStreamReader isr = new InputStreamReader(System.in);//标准输入流
		BufferedReader br = new BufferedReader(isr);//加入一个缓冲
		//负责网络通信部分的流
		//输出流
		OutputStream os=s.getOutputStream();
		PrintWriter pw = new PrintWriter(os);//将输出字节流转为字符流
	
		//输入流 用来监控服务器说出的话
		InputStream iss = s.getInputStream();
		BufferedReader bris = new BufferedReader(new InputStreamReader(iss));
		
		String line=null;
		
		do {
			System.out.println("请输入你想对服务器说的话");
			line=br.readLine();
			
			pw.println(line);
			pw.flush();
			
			String serverwords=bris.readLine();
			System.out.println("服务器回答说:"+serverwords);
			
			if("bye".equals(serverwords)) {
				System.out.println("服务器主动要求与客户端断开连接...");
				break;
			}
		} while (!(line.equals("bye")));
		
		if("bye".equals(line)) {
			System.out.println("客户端主动与服务器断开连接...");
		}
		
		System.out.println("客户端掉线...");
		
		s.close();
	}
}
