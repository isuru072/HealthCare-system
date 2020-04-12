package config;


import java.sql.Connection;
import java.sql.DriverManager;

public class dbconnector {
	
	public static  Connection connecter()
	{
		Connection con = null;
		
		String url = "jdbc:mysql://localhost:8080/hcdb";
		String username = "root";
		String password = "";
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connection Top");
			//con =  (Connection) DriverManager.getConnection(url,username,password);
			con = DriverManager.getConnection("jdbc:mysql://localhost/hcdb?user="+username+"&password="+password);
			System.out.println("Connection Done");
			if (con != null) {
                System.out.println("Connected to the fail");
            }
		}catch(Exception e) {
			System.out.println(e);
		}
		return con;
	} 
}
