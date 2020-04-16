package com.java;

import java.sql.*;
import java.util.*;


import com.resources.connect;

public class Doctor {
	
	//inserting doctor
	public String insertDoctor(int d_ID,String dfname,String dlname,String dnic,String dphone,String demail,String daddress,String dcity,String dspeciality,String dworkinghospital,String dbank,String dcardtype,String dcardno,String dcharge) {
		String output = "";
		
		try {
			Connection con = connect.connectMethod();
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "insert into doctors" + "(d_ID,dfname,dlname,dnic,dphone,demail,daddress,dcity,dspeciality,dworkinghospital,dbank,dcardtype,dcardno,dcharge)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, dfname);
			preparedStmt.setString(3, dlname);
			preparedStmt.setString(4, dnic);
			preparedStmt.setString(5, dphone);
			preparedStmt.setString(6, demail);
			preparedStmt.setString(7, daddress);
			preparedStmt.setString(8, dcity);
			preparedStmt.setString(9, dspeciality);
			preparedStmt.setString(10, dworkinghospital);
			preparedStmt.setString(11, dbank);
			preparedStmt.setString(12, dcardtype);
			preparedStmt.setString(13, dcardno);
			preparedStmt.setString(14, dcharge);
			
			//execution
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully.";
			System.out.println(output);
		} catch(Exception e) {
			output = "Error while inserting the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//view the doctors
	public String viewDoctors(){
		
		String output = "";
		
		try {
			Connection con = connect.connectMethod();
			if(con == null) {
				System.out.println("Error while reading from database");
			}
			
			//prepare the table for display
			output = "<table border=\"1\"><tr>"+"<th>Doctor ID</th>"+"<th>First Name</th>"+"<th>Last Name</th>"+"<th>NIC</th>"+"<th>Phone</th>"+"<th>Email</th>"+"<th>Address</th>"+"<th>City</th>"+"<th>Speciality</th>"+"<th>Working Hospital</th>"+"<th>Bank</th>"+"<th>Card Type</th>"+"<th>Card Number</th>"+"<th>Charge</th>"+"</tr>";
			
			
			String query = "select * from doctors";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String d_ID = Integer.toString(rs.getInt("DoctorID"));
				String dfname =rs.getString("dFirstName");
				String dlname =rs.getString("dLastName");
				String dnic =rs.getString("dNIC");
				String dphone =rs.getString("dPhone");
				String demail =rs.getString("dEmail");
				String daddress =rs.getString("dAddress");
				String dcity =rs.getString("dCity");
				String dspeciality =rs.getString("dSpeciality");
				String dworkinghospital =rs.getString("dWorkingHospital");
				String dbank =rs.getString("dBank");
				String dcardtype =rs.getString("dCardType");
				String dcardno =rs.getString("dCardNo");
				String dcharge =rs.getString("dCharge");
				
				
				//add into table
				output += "<tr><td>" + d_ID + "</td>";
				output += "<td>" + dfname + "</td>";
				output += "<td>" + dlname + "</td>";
				output += "<td>" + dnic + "</td>";
				output += "<td>" + dphone + "</td>";
				output += "<td>" + demail + "</td>";
				output += "<td>" + daddress + "</td>";
				output += "<td>" + dcity + "</td>";
				output += "<td>" + dspeciality + "</td>";
				output += "<td>" + dworkinghospital + "</td>";
				output += "<td>" + dbank + "</td>";
				output += "<td>" + dcardtype + "</td>";
				output += "<td>" + dcardno + "</td>";
				output += "<td>" + dcharge + "</td>";
				
				//buttons
				output += "<td><input name=\"btnUpdate\" " 
						+ " type=\"button\" value=\"Update\">"
					+ "</td>"
				+ "<td><form method=\"post\" action=\"items.jsp\">" 
					+ "<input name=\"btnRemove\" "
				+ " type=\"submit\" value=\"Remove\">" 
					+ "<input name=\"itemID\" type=\"hidden\" "
				+ " value=\""
				+ d_ID + "\">" + "</form></td></tr>";
				
				
			}
			con.close();
			
			//complete table
			output += "</table>";
			
		} catch(Exception e) {
			System.out.println("Error while reading");
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	//update doctors list
	public String updateDoctor(int d_ID,String dfname,String dlname,String dnic,String dphone,String demail,String daddress,String dcity,String dspeciality,String dworkinghospital,String dbank,String dcardtype,String dcardno,String dcharge) {
		String output = "";
		
		try {
			Connection con = connect.connectMethod();
			
			if(con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "update doctors set dfname=?,dlname=?,dnic=?,dphone=?,demail=?,daddress=?,dcity=?,dspeciality=?,dworkinghospital=?,dbank=?,dcardtype=?,dcardno=?,dcharge=? where d_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			//values
			preparedStmt.setString(1, dfname);
			preparedStmt.setString(2, dlname);
			preparedStmt.setString(3, dnic);
			preparedStmt.setString(4, dphone);
			preparedStmt.setString(5, demail);
			preparedStmt.setString(6, daddress);
			preparedStmt.setString(7, dcity);
			preparedStmt.setString(8, dspeciality);
			preparedStmt.setString(9, dworkinghospital);
			preparedStmt.setString(10, dbank);
			preparedStmt.setString(11, dcardtype);
			preparedStmt.setString(12, dcardno);
			preparedStmt.setString(13, dcharge);
			preparedStmt.setInt(14, d_ID);
			
			
			//execution
			preparedStmt.execute();
			con.close();
			output = "Updated successfully.";
			
			
		}catch(Exception e) {
			output = "Error while updating the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String removeDoctor(String d_ID) {
		String output = "";
		
		try {
			Connection con = connect.connectMethod();
			
			if(con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			//creating prepared statement
			String query = "delete from doctors where d_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//values
			preparedStmt.setInt(1, Integer.parseInt(d_ID));
			
			//execution
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch(Exception e) {
			output = "Error while deleting the doctor.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	

}
