package mvc.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionObject {
	public static Connection getConn() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/javadb","root","root");
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
		return con;
		
	}

}
