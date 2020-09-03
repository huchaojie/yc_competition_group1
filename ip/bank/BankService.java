package bank;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class BankService implements Taskable {
	private Socket s;
	private Bank b;
	private Scanner reader;
	private PrintWriter pw;
	private boolean flag;
	
	public BankService(Socket s,Bank b) {
		this.s=s;
		this.b=b;
		try {
			System.out.println("atm客户端:"+s.getInetAddress()+"连接了服务器");
			reader=new Scanner(s.getInputStream());
			pw=new PrintWriter(s.getOutputStream());
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
	}
	
	@Override
	public Object doTask() {
		while(flag) {
			if(!reader.hasNext()) {  //hasnext获取一行数据
				System.out.println("atm客户端:"+s.getInetAddress()+"掉线了...");
				break;
			}
			String command=reader.next();//取出命令动作
			if(command.equals("QUIT")) {
				System.out.println("atm客户端:"+s.getInetAddress()+"掉线了...");
				break;
			}
			//处理剩下三种情况
			int accountId=reader.nextInt();//账户
			try {
				BankAccount ba=null;
				if(command.equals("DEPOST")) {
					double amount=reader.nextDouble();
					ba=b.deposite(accountId, amount);
				}else if(command.equals("WITHDRAW")) {
					double amount=reader.nextDouble();
					ba=b.withdraw(accountId, amount);
				}else if(!command.equals("BALANCE")) {
					pw.println("错误的命令");
					pw.flush();
					return null;
				}
				ba=b.search(accountId);
				pw.println(ba.getId()+" "+ba.getBalance());
				pw.flush();
			} catch (Exception e) {
				pw.println("操作异常"+e.getMessage());
				pw.flush();
			}
		}
		return null;
	}


}
