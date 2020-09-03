package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) throws IOException {
		byte buf[]=new byte[1024];//字节数组 用于缓存数据
		DatagramPacket dp = new DatagramPacket(buf, buf.length); //构建一个数据包对象
		
		DatagramSocket ds = new DatagramSocket(3333);
		while(true) {
			System.out.println("server正在监听"+ds.getLocalPort());
			ds.receive(dp);
			System.out.println("server接收到"+dp.getAddress()+"的消息了...");
		
			System.out.println(new String(buf,0,dp.getLength()));//将字节数组转成做饭吃输出
		}
	}
}
