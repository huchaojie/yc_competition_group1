package Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s=new Socket("localhost",9999);
		System.out.println("客户端"+s.getLocalAddress()+"连接服务器"+s.getRemoteSocketAddress());
	
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		DataInputStream dis = new DataInputStream(s.getInputStream());
	
		dos.writeUTF("i am client");//先发送
		dos.flush();
		
		System.out.println("如果服务器没有相应的话,这里是堵塞的....");
		
		
		String ss=null;
		if((ss=dis.readUTF())!=null) {
			System.out.println("server"+ss);
		}
		
		dis.close();
		dos.flush();
		dos.close();
		s.close();
		
		System.out.println("客户端"+s.getLocalAddress()+"断开连接");
	}
}
