package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.java.Prescription;

public class PrescriptionService {
	
	Prescription press = new Prescription();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPrescription() {
		return press.readPrescription();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPrescription(@FormParam("Prescription_ID") int prescription_ID,@FormParam("Name") String pname,@FormParam("Age") int age,
			@FormParam("Medicines") String medicine,@FormParam("Count") int count) {
		String output = press.insertPrescription(prescription_ID, pname, age, medicine, count);
		
		return output;
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePrescription(String pressRecord) {
		JsonObject cription = new JsonParser().parse(pressRecord).getAsJsonObject();
		
		int prescription_ID = cription.get("Prescription_ID").getAsInt();
		String pname = cription.get("Name").getAsString();
		int age = cription.get("Age").getAsInt();
		String medicine = cription.get("Medicine").getAsString();
		int count = cription.get("Count").getAsInt();
		
		String output = press.updatePrescription(prescription_ID, pname, age, medicine, count);
		
		return output;
	}
	

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePrescription(String pressRecord) {
		Document doc = Jsoup.parse(pressRecord,"",Parser.xmlParser());
		
		String prescription_ID = doc.select("prescription_ID").text();
		
		String output = press.deletePrescription(prescription_ID);
		
		return output;
	}
	
}
