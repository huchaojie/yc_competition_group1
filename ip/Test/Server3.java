package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {

	public static void main(String[] args) throws IOException {
		// 新建serversocket
		ServerSocket ss = new ServerSocket(9995);
		System.out.println("服务端" + ss.getLocalSocketAddress() + "开始监听" + ss.getLocalPort() + "是否有客户端连接");
		BufferedReader is = null;
		PrintWriter pw = null;
		Socket socket = null;
		boolean f = true;

		while (f) {
			socket = ss.accept();// 获取一个socket
			System.out.println(socket.getInetAddress() + "连接上来了");
			String line;
			// 负责网络传输
			// socket输入流
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// socket输出流
			pw = new PrintWriter(socket.getOutputStream());
			// 键盘输入流
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String response = null;
			boolean flag = true;

			do {
				// 从客户端输入
				String request = is.readLine();
				System.out.println("客户端向服务器说:" + request);
				if ("bye".equals(request)) {
					System.out.println("客户端主动与服务器断开连接...");
					break;
				}

				// 键盘输入
				System.out.println("请输入服务器想对客户端说的话:");
				response = br.readLine();
				pw.println(response);
				pw.flush();
				if ("bye".equals(response)) {
					System.out.println("服务器主动要求与客户端断开连接...");
					break;
				}

			} while (flag);
			System.out.println("服务器断开" + socket.getInetAddress() + "连接");

		}
		is.close();
		pw.close();
		socket.close();
		ss.close();
	}
}
