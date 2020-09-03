package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Test {
	private Connection conn;

	public Connection getConn() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bike","root","a");
		return conn;
		
	}
	
	public static void main(String[] args) throws SQLException {
		Test t=new Test();
		System.out.println(t.getConn());
	}
}
