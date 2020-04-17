package com.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.config.dbconnector;

public class ScheduleService {
Connection con = null;
	
	public ScheduleService()
	{		 
		con = dbconnector.connecter();
	} 
	//inserting details
	
	public String insertSchedule(int doctor_ID,String doctor_Name,String specialization,int hospital_ID,String charges,String avail_time,String end_time) {
		String output = "";
		try {
			
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "insert into schedule" + "(schedule_ID,doctor_ID,doctor_Name,specialization,hospital_ID,charges,avail_time,end_time)" + "values(?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, doctor_ID);
			preparedStmt.setString(3, doctor_Name);
			preparedStmt.setString(4, specialization);
			preparedStmt.setInt(5, hospital_ID);
			preparedStmt.setDouble(6, Double.parseDouble(charges));
			preparedStmt.setString(7, avail_time);
			preparedStmt.setString(8, end_time);
			
			//execution
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully.";
			
		} catch(Exception e) {
			output = "Error while inserting the details,";
			System.err.println(e.getMessage());
		}
		return output;
	}
	

	//view details
	public String readSchedule() {
		String output = "";
		try {
			
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			output = "<table border=\"1\">"
					+ "<tr>"
					+ "<th>Schedule ID</th>" 
					+ "<th>Doctor ID</th>"
					+ "<th>Doctor Name</th>"
					+ "<th>Specialization</th>"
					+ "<th>Hospital ID</th>"
					+ "<th>Available Time</th>"
					+ "<th>End Time</th>"
					+ "<th>Charges</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>";
			
			String query = "select * from schedule";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//duplicate the rows
			while(rs.next()) {
				String schedule_ID = Integer.toString(rs.getInt("schedule_ID"));
				String doctor_ID = Integer.toString(rs.getInt("Doctor_ID"));
				String doctor_Name = rs.getString("Doctor_Name");
				String specialization = rs.getString("Specialization");
				String hospital_ID = Integer.toString(rs.getInt("Hospital_ID"));
				String charges = Double.toString(rs.getDouble("Charges"));
				String avail_time = rs.getString("Available_Time");
				String end_time = rs.getString("End_Time");
				
				
				//add into the table
				output += "<tr><td>" + schedule_ID + "</td>";
				output += "<td>" + doctor_ID + "</td>";
				output += "<td>" + doctor_Name + "</td>";
				output += "<td>" + specialization + "</td>";
				output += "<td>" + hospital_ID + "</td>";
				output += "<td>" + avail_time + "</td>";
				output += "<td>" + end_time + "</td>";
				output += "<td>" + charges + "</td>";
				
				//buttons
				output += "<td><input name=\"btnUpdate\" " 
						+ " type=\"button\" value=\"Update\">"
					+ "</td>"
				+ "<td><form method=\"post\" action=\"items.jsp\">" 
					+ "<input name=\"btnRemove\" "
				+ " type=\"submit\" value=\"Remove\">" 
					+ "<input name=\"itemID\" type=\"hidden\" "
				+ " value=\""
				+ schedule_ID + "\">" + "</form></td></tr>";

				
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
	
	//update schedule
	public String updateSchedule(int schedule_ID,int doctor_ID,String doctor_Name,String specialization,int hospital_ID,double charges,String avail_time,String end_time) {
		String output = "";
		try {
			
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "update schedule set doctor_ID=?,doctor_Name=?,specialization=?,hospital_ID=?,charges=?,avail_time=?,end_time=? where schedule_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//values
			preparedStmt.setInt(1, doctor_ID);
			preparedStmt.setString(2, doctor_Name);
			preparedStmt.setString(3, specialization);
			preparedStmt.setInt(4, hospital_ID);
			preparedStmt.setDouble(5, charges);
			preparedStmt.setString(6, avail_time);
			preparedStmt.setString(7, end_time);
			preparedStmt.setInt(9, schedule_ID);
			
			//execution
			preparedStmt.execute();
			con.close();
			output = "Updated Successfully.";
		} catch(Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteSchedule(String schedule_ID) {
		String output = "";
		try {
			
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "delete from schedule where schedule_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(1, Integer.parseInt(schedule_ID));
			
			//execution
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			
		}catch(Exception e) {
			output = "Error while deleting the details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	


}
