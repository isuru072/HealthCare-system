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
import com.service.ScheduleService;

@Path("/Schedule")
public class ScheduleController {
ScheduleService scheduleObj = new ScheduleService();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readSchedule() {
		return scheduleObj.readSchedule();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertSchedule(@FormParam("Doctor_ID") int doctor_ID,
			@FormParam("Doctor_Name") String doctor_Name,
			@FormParam("Specialization") String specialization,
			@FormParam("Hospital_ID") int hospital_ID,
			@FormParam("Chargers") String charges,
			@FormParam("Available_Time") String avail_time,
			@FormParam("End_Time") String end_time) {
		
		String output = scheduleObj.insertSchedule(doctor_ID, doctor_Name, specialization, hospital_ID, charges, avail_time, end_time);
		
		return output;
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSchedule(String scheduleRecord) {
		JsonObject schedule = new JsonParser().parse(scheduleRecord).getAsJsonObject();
		
		int schedule_ID = schedule.get("Schedule_ID").getAsInt();
		int doctor_ID = schedule.get("Doctor_ID").getAsInt();
		String doctor_Name = schedule.get("Doctor_Name").getAsString();
		String specialization = schedule.get("Specialization").getAsString();
		int hospital_ID = schedule.get("Hospital_ID").getAsInt();
		double charges = schedule.get("Charges").getAsDouble();
		String avail_time = schedule.get("Avail_Time").getAsString();
		String end_time =  schedule.get("End_Time").getAsString();
		
		String output = scheduleObj.updateSchedule(schedule_ID, doctor_ID, doctor_Name, specialization, hospital_ID, charges, avail_time, end_time);
		
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteSchedule(String scheduleRecord) {
		Document doc = Jsoup.parse(scheduleRecord,"",Parser.xmlParser());
		
		String schedule_ID = doc.select("schedule_ID").text();
		
		String output = scheduleObj.deleteSchedule(schedule_ID);
		
		return output;
	}
	
}
