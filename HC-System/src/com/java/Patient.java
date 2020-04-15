package com.java;

//import file
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import com.resources.connect;

public class Patient {
	
	//inserting patients
	
	public String insertPatient(int patient_ID,String username,String password,String fname,String lname,String gender,String phone,String nic,String dob,String email,String address,String blood,String status) {
		String output = "";
		try {
			Connection con = connect.connectMethod();
			if(con == null) {
				return "Error while connecting to the database for inseerting.";
			}
			
			//creating prepared statement
			String query = "insert into patients" + "(patient_id,username,password,fname,lname,gender,phone,nic,dob,email,address,blood,status)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			//values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, username);
			preparedStmt.setString(3, password);
			preparedStmt.setString(4, fname);
			preparedStmt.setString(5, lname);
			preparedStmt.setString(6, gender);
			preparedStmt.setString(7, phone);
			preparedStmt.setString(8, nic);
			preparedStmt.setString(9, dob);
			preparedStmt.setString(10, email);
			preparedStmt.setString(11, address);
			preparedStmt.setString(12, blood);
			preparedStmt.setString(13, status);
			
			
			//execution
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
			
		}catch(Exception e) {
			output = "Error while inserting the patients";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//view patients
	
	public String readPatient(){
		
		String output = "";
		
		try {
			Connection con = connect.connectMethod();
			if(con == null) {
				System.out.println("Error while connecting to the database for reading");
			}
			
			output = "<table border=\"1\">"
					+ "<tr>"
					+ "<th>Patient ID</th>" 
					+ "<th>Username</th>"
					+ "<th>Password</th>"
					+ "<th>First Name</th>"
					+ "<th>Last Name</th>"
					+ "<th>Gender</th>"
					+ "<th>Phone</th>"
					+ "<th>NIC</th>"
					+ "<th>DOB</th>"
					+ "<th>Email</th>"
					+ "<th>Address</th>"
					+ "<th>Blood Group</th>"
					+ "<th>Marital Status</th>"
					+ "<th>UPDATE</th>"
					+ "<th>REMOVE</th>"
					+ "</tr>";
			
			String query = "select * from patients";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//duplicate the rows
			while(rs.next()) {
				String patient_ID = Integer.toString(rs.getInt("patientID"));
				String username = rs.getString("Username");
				String password = rs.getString("Password");
				String fname = rs.getString("FirstName");
				String lname = rs.getString("LastName");
				String gender = rs.getString("Gender");
				String phone = rs.getString("Phone");
				String nic = rs.getString("NIC");
				String dob = rs.getString("DOB");
				String email = rs.getString("Email");
				String address = rs.getString("Address");
				String blood = rs.getString("BloodGroup");
				String status = rs.getString("MaritalStatus");
				
				
				//add into the table
				output += "<tr><td>" + patient_ID + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + fname + "</td>";
				output += "<td>" + lname + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + dob + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + blood + "</td>";
				output += "<td>" + status + "</td>";
				
				
				//buttons
				output += "<td><input name=\"btnUpdate\" " 
						+ " type=\"button\" value=\"Update\">"
					+ "</td>"
				+ "<td><form method=\"post\" action=\"items.jsp\">" 
					+ "<input name=\"btnRemove\" "
				+ " type=\"submit\" value=\"Remove\">" 
					+ "<input name=\"itemID\" type=\"hidden\" "
				+ " value=\""
				+ patient_ID + "\">" + "</form></td></tr>";
					
			}
			con.close();
			
			//complete the table
			output += "</table>";
			
			}catch(Exception e) {
				output = "Error while reading the Patient details.";
				System.err.println(e.getMessage());
			}
			return output;
			
		}
	
	//updating patient details
	public String updatePatient(int patient_ID,String username,String password,String fname,String lname,String gender,String phone,String nic,String dob,String email,String address,String blood,String status) {
		String output = "";
		try {
			Connection con = connect.connectMethod();
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "update patient set username=?,password=?,fname=?,lname=?,gender=?,phone=?,nic=?,dob=?,email=?,address=?,blood=?,status=? where patient_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//values
			preparedStmt.setInt(1, patient_ID);
			preparedStmt.setString(2, username);
			preparedStmt.setString(3, password);
			preparedStmt.setString(4, fname);
			preparedStmt.setString(5, lname);
			preparedStmt.setString(6, gender);
			preparedStmt.setString(7, phone);
			preparedStmt.setString(8, nic);
			preparedStmt.setString(9, dob);
			preparedStmt.setString(10, email);
			preparedStmt.setString(11, address);
			preparedStmt.setString(12, blood);
			preparedStmt.setString(13, status);
			
			//execution
			preparedStmt.execute();
			con.close();
			output = "Updated Successfully.";
		}catch(Exception e) {
			output = "Error while updating the details.";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	
	
	//deleting patient details
	public String deletePatient(String patient_ID) {
		
		String output = "";
		try {
			Connection con = connect.connectMethod();
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "delete from patient where patient_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(1, Integer.parseInt(patient_ID));
			
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
