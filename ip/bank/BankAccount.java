package bank;

//银行的账户
public class BankAccount {

	private int id;
	private double balance;
	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BankAccount(int id, double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", balance=" + balance + "]";
	}
	
	
}
