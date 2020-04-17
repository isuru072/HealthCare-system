package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.config.dbconnector;

public class AppointmentService {

	Connection con = null;

	
	public AppointmentService()
	{		 
		con = dbconnector.connecter();
	} 
	
	public String readAppointment() {
		
		String output = "";
		
		try {
			
			if(con == null) {
				return "Error while connecting to the database.";
			}
			//prepare the table for display
			output = "<table border=\"1\"><tr>"+"<th>AppointmentID</th>"+"<th>Checked Status</th>"+"<th>Token Number</th>"+"<th>Patient Status</th>"+"<th>Patient Phone</th>"+"<th>Patient Name</th>"+"<th>DoctorID</th>"+"<th>ScheduleID</th>"+"<th>HospitalID</th>"+"</tr>";
			
			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//duplicate the rows
			while(rs.next()) {
				String appointment_ID = Integer.toString(rs.getInt("AppointmentID"));
				String checkedStatus =rs.getString("CheckedStatus");
				String tokenNum =rs.getString("TokenNumber");
				String patientstatus =rs.getString("PatientStatus");
				String patientphone =rs.getString("PatientPhone");
				String patientname =rs.getString("PatientName");
				String d_ID = Integer.toString(rs.getInt("d_ID"));
				String schedule_ID = Integer.toString(rs.getInt("scheduleID"));
				String hospital_ID = Integer.toString(rs.getInt("hospitalID"));
				
				//add into table
				output += "<tr><td>" + appointment_ID + "</td>";
				output += "<td>" + checkedStatus + "</td>";
				output += "<td>" + tokenNum + "</td>";
				output += "<td>" + patientstatus + "</td>";
				output += "<td>" + patientphone + "</td>";
				output += "<td>" + patientname + "</td>";
				output += "<td>" + d_ID + "</td>";
				output += "<td>" + schedule_ID + "</td>";
				output += "<td>" + hospital_ID + "</td>";
				
				//buttons
				output += "<td><input name=\"btnUpdate\" " 
						+ " type=\"button\" value=\"Update\">"
					+ "</td>"
				+ "<td><form method=\"post\" action=\"items.jsp\">" 
					+ "<input name=\"btnRemove\" "
				+ " type=\"submit\" value=\"Remove\">" 
					+ "<input name=\"itemID\" type=\"hidden\" "
				+ " value=\""
				+ appointment_ID + "\">" + "</form></td></tr>";
				
				
			}
			con.close();
			
			//complete table
			output += "</table>";
						
					
		} catch(Exception e) {
			output = "Error while reading the appointments.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String insertAppointment(int appointment_ID,String checkedStatus,String tokenNum,String patientstatus,String patientphone,String patientname,int d_ID,int schedule_ID,int hospital_ID) {
		String output = "";
		try {
			
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			//creating prepared statement
			String query = "insert into appointment (appointment_ID,checkedStatus,tokenNum,patientstatus,patientphone,patientname,d_ID,schedule_ID,hospital_ID)" + "values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, checkedStatus);
			preparedStmt.setString(3, tokenNum);
			preparedStmt.setString(4, patientstatus);
			preparedStmt.setString(5, patientphone);
			preparedStmt.setString(6, patientname);
			preparedStmt.setInt(7, d_ID);
			preparedStmt.setInt(8, schedule_ID);
			preparedStmt.setInt(9, hospital_ID);
			
			
			//execute
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		} catch(Exception e) {
			output = "Error while inserting the appointment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateAppointment(int appointment_ID,String checkedStatus,String tokenNum,String patientstatus,String patientphone,String patientname,int d_ID,int schedule_ID,int hospital_ID) {
		String output = "";
		try {
			
			if(con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			//creating prepared statement 
			String query = "update appointment set" + "checkedStatus=?, "+ "tokenNum=?, "+ "patientstatus=?, "+ "patientphone=?, "+ "patientname=?, "+"d_ID=?, "+ "scheduleID=?, " + "hospitalID=?, "+ "where appointment_ID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//values
			preparedStmt.setString(1, checkedStatus);
			preparedStmt.setString(2, tokenNum);
			preparedStmt.setString(3, patientstatus);
			preparedStmt.setString(4, patientphone);
			preparedStmt.setString(5, patientname);
			preparedStmt.setInt(6, d_ID);
			preparedStmt.setInt(7, schedule_ID);
			preparedStmt.setInt(8, hospital_ID);
			preparedStmt.setInt(9, appointment_ID);
			
			//execute
			preparedStmt.execute();
			
			con.close();
			
			output = "Updated successfully";
			
		
			
		} catch(Exception e) {
			output = "Error while updating the appointment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteAppointment(String appointment_ID) {
		String output = "";
		try {
			
			if(con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			//creating prepared statement
			String query = "delete from appointment where appointment_ID = ?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//value
			preparedStmt.setInt(1, Integer.parseInt(appointment_ID));
			
			//execute
			preparedStmt.execute();
			con.close();
			output = "deleting successfully";
		} catch(Exception e) {
			output = "Error while deleting the appointment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
