package com.conrollers;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.service.AppointmentService;

@Path("/Appointment")
public class AppointmentController {
AppointmentService appointmentObj = new AppointmentService();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointment() {
		String output = appointmentObj.readAppointment();
		return output;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("Appointment_ID") int appointment_ID,@FormParam("CheckedStatus") String checkedStatus,@FormParam("TokenNumber") String tokenNum,@FormParam("PatientStatus") String patientstatus,@FormParam("PatientPhone") String patientphone,@FormParam("PatientName") String patientname,@FormParam("D_ID") int d_ID,@FormParam("Schedule_ID") int schedule_ID,@FormParam("Hospital_ID") int hospital_ID) {
	
		String output = appointmentObj.insertAppointment(appointment_ID, checkedStatus, tokenNum, patientstatus, patientphone, patientname, d_ID, schedule_ID, hospital_ID);
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String appointRecord) {
		JsonObject appoint = new JsonParser().parse(appointRecord).getAsJsonObject();
		
		int appointment_ID = appoint.get("Appointment_ID").getAsInt();
		String checkedStatus = appoint.get("CheckedStatus").getAsString();
		String tokenNum = appoint.get("TokenNumber").getAsString();
		String patientstatus = appoint.get("PatientStatus").getAsString();
		String patientphone = appoint.get("PatientPhone").getAsString();
		String patientname = appoint.get("PatientName").getAsString();
		int d_ID = appoint.get("Appointment_ID").getAsInt();
		int schedule_ID = appoint.get("Appointment_ID").getAsInt();
		int hospital_ID = appoint.get("Appointment_ID").getAsInt();
		
		String output = appointmentObj.updateAppointment(appointment_ID, checkedStatus, tokenNum, patientstatus, patientphone, patientname, d_ID, schedule_ID, hospital_ID);
		
		return output;
		
		
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String appointRecord) {
		
		Document doc = Jsoup.parse(appointRecord,"",Parser.xmlParser());
		
		String appointment_ID = doc.select("Appointment_ID").text();
		
		String output = appointmentObj.deleteAppointment(appointment_ID);
		
		return output;
		
	}
}
