package com.service;




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

import com.java.Doctor;


@Path("/Doctors")
public class DoctorService {

	Doctor objDoctor = new Doctor();
	
	//view a Doctor
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String viewDoctors(){
		return objDoctor.viewDoctors();
	}
	
	//insert a Doctor
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("Doctor_ID") int d_ID,@FormParam("dFirstName") String dfname,@FormParam("dLastName") String dlname,@FormParam("dNIC") String dnic,@FormParam("dPhone") String dphone,@FormParam("dEmail") String demail,@FormParam("dAddress") String daddress,@FormParam("dCity") String dcity,@FormParam("dSpeciality") String dspeciality,@FormParam("dWorkingHospital") String dworkinghospital,@FormParam("dBank") String dbank,@FormParam("dCardType") String dcardtype,@FormParam("dCardNumber") String dcardno,@FormParam("dCharge") String dcharge) {
		
		String output = objDoctor.insertDoctor(d_ID, dfname, dlname, dnic, dphone, demail, daddress, dcity, dspeciality, dworkinghospital, dbank, dcardtype, dcardno, dcharge);
		
		return output;
		
	}
	
	
	//update a doctor
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String doctorRecord) {
		
		JsonObject doctor = new JsonParser().parse(doctorRecord).getAsJsonObject();
		
		int d_ID = doctor.get("d_ID").getAsInt();
		String dfname = doctor.get("firstname").getAsString();
		String dlname = doctor.get("lastname").getAsString();
		String dnic = doctor.get("NIC").getAsString();
		String dphone = doctor.get("phone").getAsString();
		String demail = doctor.get("email").getAsString();
		String daddress = doctor.get("address").getAsString();
		String dcity = doctor.get("city").getAsString();
		String dspeciality = doctor.get("speciality").getAsString();
		String dworkinghospital = doctor.get("workinghospital").getAsString();
		String dbank = doctor.get("bank").getAsString();
		String dcardtype = doctor.get("cardtype").getAsString();
		String dcardno = doctor.get("cardno").getAsString();
		String dcharge = doctor.get("charge").getAsString();
		
		String output = objDoctor.updateDoctor(d_ID, dfname, dlname, dnic, dphone, demail, daddress, dcity, dspeciality, dworkinghospital, dbank, dcardtype, dcardno, dcharge);
		
		return output;
		
		
		
	}
	
	
	//remove a doctor
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String removeDoctor(String doctorRecord) {
		
		Document doc = Jsoup.parse(doctorRecord,"",Parser.xmlParser());
		
		String d_ID = doc.select("d_ID").text();
		
		String output = objDoctor.removeDoctor(d_ID);
		return output;
		
	}
	
	
	
}
