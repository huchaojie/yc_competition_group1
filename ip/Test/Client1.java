package Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s=new Socket("localhost",6666);
		System.out.println("客户端准备连接中...");
		try {
			Thread.sleep(10000); //加入阻塞的含义  服务器等待
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		OutputStream oos=s.getOutputStream();//输出流
		DataOutputStream dos=new DataOutputStream(oos);
		
		dos.writeUTF("lei zhi");
		
		dos.flush();
		dos.close();
		s.close();
	}
}
