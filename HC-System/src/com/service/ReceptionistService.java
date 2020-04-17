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

import com.java.Receptionist;

@Path("/Receptionists")
public class ReceptionistService {
	
	Receptionist objRecep = new Receptionist();
	
	//read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readReceptionist() {
		return objRecep.readReceptionist();
	}
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertReceptionist(@FormParam("Receptionist_ID") int rep_ID,@FormParam("Password") String password,@FormParam("Name") String name,@FormParam("Gender") String gender,@FormParam("Email") String email,@FormParam("Phone") String phone,@FormParam("Address") String address) {
		
		String output = objRecep.insertReceptionist(rep_ID, password, name, gender, email, phone, address);
		
		return output;
		
	}
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateReceptionist(String recepRecord) {
		JsonObject recep = new JsonParser().parse(recepRecord).getAsJsonObject();
		
		
		int rep_ID = recep.get("Receptionist_ID").getAsInt();
		String password = recep.get("Password").getAsString();
		String name = recep.get("Name").getAsString();
		String gender = recep.get("Gender").getAsString();
		String email = recep.get("Email").getAsString();
		String phone = recep.get("Phone").getAsString();
		String address = recep.get("Address").getAsString();
		
		String output = objRecep.updateReceptionist(rep_ID, password, name, gender, email, phone, address);
		
		return output;
		
	}
	
	//remove
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteReceptionist(String recepRecord) {
		Document doc = Jsoup.parse(recepRecord,"",Parser.xmlParser());
		
		String rep_ID = doc.select("rep_ID").text();
		
		String output = objRecep.deleteReceptionist(rep_ID);
		
		return output;
	}

}
