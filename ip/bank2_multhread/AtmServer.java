package bank2_multhread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class AtmServer {
	static boolean flag=true;

	public static void main(String[] args) throws IOException {
		ThreadPoolManger tpm=new ThreadPoolManger(10,null);
		
		Bank b=new Bank();
		ServerSocket ss=new ServerSocket(13000);
		System.out.println("银行服务器启动...监听"+ss.getLocalPort()+"端口");
		
		//还有ServerSocket  监听 13001
		//接收客户端 请求-> tomcat 8080  8009
		
		
		while(true) {
				Socket s=ss.accept();
//				Thread t=new Thread(new BankService(s,b));
//				t.setDaemon(true);
//				t.start();
			Taskable t=new BankService(s,b);
			tpm.process(t);
		}
	}
}


	 
