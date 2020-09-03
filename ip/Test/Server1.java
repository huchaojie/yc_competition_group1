package Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

	public static void main(String[] args) throws IOException {
		ServerSocket ss=new ServerSocket(6666);
		System.out.println("服务器监听端口"+ss.getLocalPort());
		while(true) {
			Socket s=ss.accept();
			System.out.println(s.getInetAddress()+"连接上来了");
			DataInputStream dis=new DataInputStream(s.getInputStream());
			System.out.println(dis.readUTF());
			
			dis.close();
			s.close();
		}
	}
}
