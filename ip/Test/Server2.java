package Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket server=new ServerSocket(9999);
		System.out.println("服务端"+server.getLocalSocketAddress()+"开始监听"+server.getLocalPort()+"是否有客户端连接");
		boolean flag=true;
		while(flag) {
			Socket s=server.accept();
			System.out.println(s.getInetAddress()+"连接上来了...");
			
			OutputStream os = s.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
			
			//因为是客户端先发信息  所以服务器要先接受
			String ss=null;
			//获取客户端说的话
			if((ss=dis.readUTF())!=null) {
				//输出了客户端ip及它使用的发送端口
				System.out.println(s.getInetAddress()+",#"+s.getPort()+"说"+ss);
			}
			
			Thread.sleep(10000);
			
			dos.writeUTF("hello ,client ,i am  server");
			dos.flush();
			os.flush();
			
			dos.close();
			os.close();
			
			dis.close();
			s.close();
			System.out.println("服务器断开"+s.getInetAddress()+"连接");
		}
		System.out.println("服务器关闭");
	
	}
}
