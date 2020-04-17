package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Prescription {
	
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
	
	
	public String readPrescription() {
		
		String output = "";
		try {
			Connection con = connectMethod();
			if(con == null) {
				return "Error while connecting to the database.";
			}
			
			//prepare the table for display
			output = "<table border=\"1\"><tr>"+"<th>Prescription_ID</th>"+"<th>Patient Name</th>"+"<th>Age</th>"+"<th>Medicine</th>"+"<th>Count</th>"+"</tr>";
			
			String query = "select * from prescription";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//duplicate the rows
			while(rs.next()) {
				String prescription_ID = Integer.toString(rs.getInt("Prescription_ID"));
				String pname =rs.getString("Patient Name");
				String age =Integer.toString(rs.getInt("Age"));
				String medicine =rs.getString("Medicine");
				String count =Integer.toString(rs.getInt("Count"));
				
				
				//add into table
				output += "<tr><td>" + prescription_ID + "</td>";
				output += "<td>" + pname + "</td>";
				output += "<td>" + age + "</td>";
				output += "<td>" + medicine + "</td>";
				output += "<td>" + count + "</td>";
				
				
				//buttons
				output += "<td><input name=\"btnUpdate\" " 
						+ " type=\"button\" value=\"Update\">"
					+ "</td>"
				+ "<td><form method=\"post\" action=\"items.jsp\">" 
					+ "<input name=\"btnRemove\" "
				+ " type=\"submit\" value=\"Remove\">" 
					+ "<input name=\"itemID\" type=\"hidden\" "
				+ " value=\""
				+ prescription_ID + "\">" + "</form></td></tr>";
				
				
			}
			
			con.close();
			
			//complete table
			output += "</table>";
			
			
		}catch(Exception e) {
			output = "Error while reading the prescription.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	public String insertPrescription(int prescription_ID,String pname,int age,String medicine,int count) {
		
		String output = "";
		try {
			Connection con = connectMethod();
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			//creating prepared statement
			String query = "insert into prescription" + "(prescription_ID,pname,age,medicine,count)" + "values(?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pname);
			preparedStmt.setInt(3, age);
			preparedStmt.setString(4, medicine);
			preparedStmt.setInt(5, count);
			
			//execute
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully.";
		}catch(Exception e) {
			output = "Error while inserting the prescription";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	
public String updatePrescription(int prescription_ID,String pname,int age,String medicine,int count) {
	
	String output = "";
	try {
		Connection con = connectMethod();
		if(con == null) {
			return "Error while connecting to the database for updating.";
		}
		
		//creating prepared statement
		String query = "update prescription set" + "pname=?,age=?,medicine=?,count=? where prescription_ID=?";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		//values
		preparedStmt.setInt(1, prescription_ID);
		preparedStmt.setString(2, pname);
		preparedStmt.setInt(3, age);
		preparedStmt.setString(4, medicine);
		preparedStmt.setInt(5, count);
		
		//execution
		preparedStmt.execute();
		con.close();
		output = "Updated successfully.";
	}catch(Exception e) {
		output = "Error while updating the prescription.";
		System.err.println(e.getMessage());
	}
	return output;
	
}

public String deletePrescription(String prescription_ID) {
	String output = "";
	try {
		Connection con = connectMethod();
		if(con == null) {
			return "Error while connecting to the database for deleting.";
		}
		
		//creating prepared statement
		String query = "delete from prescription where prescription_ID=?";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		
		//values
		preparedStmt.setInt(1, Integer.parseInt(prescription_ID));
		
		
		//execution
		preparedStmt.execute();
		con.close();
		output = "Deleting successfully.";
	}catch(Exception e) {
		output = "Error while deleting the prescription.";
		System.err.println(e.getMessage());
	}
	
	return output;
}
	

}
