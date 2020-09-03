package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPClient {

	public static void main(String[] args) throws IOException {
		String s="hello world";
		//注意：DatagramPacket的构造方法参数
		//三个参：最后一个参数表示另一台机器的地址和端口
		//udp中的每一个包都必须指明要发送的地址
		DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length,new InetSocketAddress("127.0.0.1",3333));
		//使用本机上的哪一个端口发送
		DatagramSocket ds = new DatagramSocket(5678);
		ds.send(dp);
		ds.close();
		
	}
}
