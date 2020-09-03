package bank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import bank.AtmServer.Notify;


public class AtmServer {
	static boolean flag=true;

	public static void main(String[] args) throws IOException {
		Bank b=new Bank();
		ServerSocket ss=new ServerSocket(13000);
		System.out.println("银行服务器启动...监听"+ss.getLocalPort()+"端口");
		
		//还有ServerSocket  监听 13001
		//接收客户端 请求-> tomcat 8080  8009
		
		Notify n=new Notify() {
			@Override
			public void notifyResult(boolean f) {
				flag=f;
			}
		};
		
		Thread th=new Monitior(n,ss);
		th.start();
		
		while(flag) {
			try {
				Socket s=ss.accept();
//				Thread t=new Thread(new BankService(s,b));
//				t.setDaemon(true);
//				t.start();
			} catch (Exception e) {
			}
		}
		System.out.println("服务器正常关闭...");
	}
	
	interface Notify{
		public void notifyResult(boolean flag);
	}
}

class Monitior extends Thread{
	//public boolean flag=true;
	private Notify n;
	private ServerSocket ss1;
	
	public Monitior(Notify n,ServerSocket ss1) {
		this.ss1=ss1;
	}
	
	//	监督关闭服务器的指令
	@Override
	public void run() {
		try {
			ServerSocket ss=new ServerSocket(13334);
			Socket s=ss.accept();
			System.out.println(s.getInetAddress()+"连接上来");
			Scanner sc=new Scanner(s.getInputStream());
			String shift;
			if(sc.hasNext()){
				shift=sc.nextLine();
				if("STOP".equals(shift)){
					if(n!=null) {
						n.notifyResult(false);
					}
					System.out.println("关闭银行服务器");
					ss1.close();
				}else if("2".equals(shift)){
					//flag=true;
					System.out.println("继续运行");
				}else{
					System.out.println("请重新输入");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	 
}
