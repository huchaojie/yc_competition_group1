package bank2_multhread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class AtmClient2 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s=new Socket("localhost",13334);
		
		PrintWriter pw = new PrintWriter(s.getOutputStream());
		
		pw.println("STOP");
		pw.flush();
		
		s.close();
		pw.close();
		System.out.println("服务器与客户端断开连接...");
	}
}
