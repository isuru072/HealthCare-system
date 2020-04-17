package com.controllers;

import javax.ws.rs.FormParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.service.PatientService;


@Path("/Patients")
public class PatientController {

	PatientService PatientObj = new PatientService();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPatient() {
		return PatientObj.readPatient();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("Patient_ID") int patient_ID,@FormParam("Username") String username,@FormParam("Password") String password,@FormParam("FirstName")String fname,@FormParam("LastName") String lname,@FormParam("Gender") String gender,@FormParam("Phone") String phone,@FormParam("NIC")String nic,@FormParam("DOB") String dob,@FormParam("Email")String email ,@FormParam("Address") String address,@FormParam("BloodGroup")String blood,@FormParam("MaritalStatus") String status) {
		String output = PatientObj.insertPatient(patient_ID,username, password, fname, lname, gender, phone, nic, dob, email, address, blood, status);
		
		return output;
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String patientRecord) {
		JsonObject patient = new JsonParser().parse(patientRecord).getAsJsonObject();
		
		int patient_ID = patient.get("patient_ID").getAsInt();
		String username = patient.get("Username").getAsString();
		String password = patient.get("Password").getAsString();
		String fname = patient.get("Firstname").getAsString();
		String lname = patient.get("Lastname").getAsString();
		String gender = patient.get("Gender").getAsString();
		String phone = patient.get("Phone").getAsString();
		String nic = patient.get("NIC").getAsString();
		String dob = patient.get("DOB").getAsString();
		String email = patient.get("Email").getAsString();
		String address = patient.get("Address").getAsString();
		String blood = patient.get("BloodGroup").getAsString();
		String status = patient.get("MaritalStatus").getAsString();
		
		String output = PatientObj.updatePatient(patient_ID, username, password, fname, lname, gender, phone, nic, dob, email, address, blood, status);
		
		return output;
		
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientRecord) {
		Document doc = Jsoup.parse(patientRecord,"",Parser.xmlParser());
		
		String patient_ID = doc.select("patient_ID").text();
		
		String output = PatientObj.deletePatient(patient_ID);
		
		return output;
	}
	
	
	
	
}