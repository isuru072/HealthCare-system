package com.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.resources.connect;

public class AuthPatient {
	
	public static boolean auth(String username,String password) {
		try {
			Connection con = connect.connectMethod();
			if(con == null) {
				
			}
			
			//creating prepared statement
			String query = "select * from users where username='"+username+"' and password='"+password+"'";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.execute();
			
			//execute
			ResultSet rs = preparedStmt.executeQuery(query);
			
			if(rs.next()) {
				con.close();
				return true;
			}
			con.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

}
