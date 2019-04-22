package library.util;

import java.sql.*;

public class DbUtil {

	private static Connection con = null;
	public static Connection getConnection() 
	{
		if (con !=null) {return con;}
		else
		{
			try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "1234");
			}
			catch(Exception e) {System.out.println(e);}
			return con;
		}
	}
}
