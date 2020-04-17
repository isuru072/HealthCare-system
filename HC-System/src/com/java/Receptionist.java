package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Receptionist {
	
	//connection
		private Connection connectMethod() {
			Connection con = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare","root", "");
				
				System.out.println("Success");
			}catch(Exception e) {
				e.printStackTrace();
			}
			return con;
		}
	
	
	//inserting details
	public String insertReceptionist(int rep_ID,String password,String name,String gender,String email,String phone,String address) {
		
		String output = "";
		
		try {
			Connection con = connectMethod();
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "insert into receptionist" + "(rep_ID,password,name,gender,email,phone,address)" + "values(?,?,?,?,?,?,?)" ;
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, password);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, phone);
			preparedStmt.setString(7, address);
			
			
			//execution
			preparedStmt.execute();
			con.close();
			output = "Inserted Successfully.";
		}catch(Exception e) {

			output = "Error while inserting the details,";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	
//view details
	public String readReceptionist() {
		
		String output = "";
		try {

			Connection con = connectMethod();
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			
			output = "<table border=\"1\">"
					+ "<tr>"
					+ "<th>Receptionist ID</th>" 
					+ "<th>Password</th>"
					+ "<th>Name</th>"
					+ "<th>Gender</th>"
					+ "<th>Email</th>"
					+ "<th>Phone</th>"
					+ "<th>Address</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>";
			
			String query = "select * from receptionist";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//duplicate the rows
			while(rs.next()) {
				String receptionist_ID = Integer.toString(rs.getInt("receptionist_ID"));
				String password = rs.getString("Password");
				String name = rs.getString("Name");
				String gender = rs.getString("Gender");
				String email = rs.getString("Email");
				String phone = rs.getString("Phone");
				String address = rs.getString("Address");
				
				
				//add into the table
				output += "<tr><td>" + receptionist_ID + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + address + "</td>";
				
				
				//buttons
				output += "<td><input name=\"btnUpdate\" " 
						+ " type=\"button\" value=\"Update\">"
					+ "</td>"
				+ "<td><form method=\"post\" action=\"items.jsp\">" 
					+ "<input name=\"btnRemove\" "
				+ " type=\"submit\" value=\"Remove\">" 
					+ "<input name=\"itemID\" type=\"hidden\" "
				+ " value=\""
				+ receptionist_ID + "\">" + "</form></td></tr>";

				
			}
			con.close();
			//complete the table
			output += "</table>";	
		}catch(Exception e) {
			output = "Error while reading the Schedule.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//update details
	public String updateReceptionist(int rep_ID,String password,String name,String gender,String email,String phone,String address) {
		
		String output = "";
		try {
			Connection con = connectMethod();
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "update receptionist set password=?,name=?,gender=?,email=?,phone=?,address=? where rep_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			//values
			preparedStmt.setInt(1, rep_ID);
			preparedStmt.setString(2, password);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, phone);
			preparedStmt.setString(7, address);
			
			
			//execution
			preparedStmt.execute();
			con.close();
			output  = "Updated Successfully.";
		}catch(Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//remove details
	public String deleteReceptionist(String rep_ID) {
		
		String output = "";
		try {
			Connection con = connectMethod();
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "delete from receptionist where rep_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(1, Integer.parseInt(rep_ID));
			
			//execution
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully.";
		}catch(Exception e) {

			output = "Error while deleting the details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}
