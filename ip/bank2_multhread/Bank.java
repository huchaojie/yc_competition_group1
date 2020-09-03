package bank2_multhread;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

public class Bank {

	private List<BankAccount> bas=new ArrayList<BankAccount>();

	public Bank() {
		for(int i=1;i<10;i++) {
			bas.add(new BankAccount(i,10));
		}
	}
	
	//查询操作
	public BankAccount search(int id) {
		BankAccount ba=null;
		for(BankAccount t:bas) {
			if(t.getId()==id) {
				ba=t;
				break;
			}
		}
		if(ba==null) {
			throw new RuntimeException("bankaccount"+id+"does not exists");
		}
		return ba;
	}
	
	//存操作
	public BankAccount deposite(int id,double money) {
		BankAccount ba=search(id);
		synchronized (this) {
			ba.setBalance(ba.getBalance()+money);
		}
		return ba;
	}
	
	//取操作
	public BankAccount withdraw(int id,double money) {
		BankAccount ba=search(id);
		if(ba.getBalance()<money) {
			throw new RuntimeException("bankaccount"+id+"does not have enough money");
		}
		synchronized (this) {
			ba.setBalance(ba.getBalance()-money);
		}
		return ba;
	}
}
