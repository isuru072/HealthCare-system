package com.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.controller.cntStock;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.java.stock;





@Path("/Stock")
public class resStock {
	
	stock appdata = new stock();
	
	
	//Read API
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readstock()
		{
			cntStock appdata = new cntStock();
			
		return appdata.readstock();
		}
	
	
	
	
	//Insert API
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertstock(String appobj)
		{
			JsonObject apdat = new JsonParser().parse(appobj).getAsJsonObject();
			
			 int id = apdat.get("id").getAsInt();
			 String mname = apdat.get("mname").getAsString();
			 String description = apdat.get("description").getAsString();
			 int amount = apdat.get("amount").getAsInt();
			 int price = apdat.get("price").getAsInt();
			 
			 cntStock apdat2 = new cntStock();
			 
			 appdata.setId(id);
			 appdata.setMname(mname);
			 appdata.setDescription(description);
			 appdata.setAmount(amount);
			 appdata.setPrice(price);
			 
			
			 String output = apdat2.insertstock(appdata);
				return output;
		}
		
		
		
		//Update API
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateHospital(String appobject)
		{
		//Convert the input string to a JSON object
		 JsonObject apdat = new JsonParser().parse(appobject).getAsJsonObject();
		//Read the values from the JSON object
		 int id = apdat.get("id").getAsInt();
		 String mname = apdat.get("mname").getAsString();
		 String description = apdat.get("description").getAsString();
		 int amount = apdat.get("amount").getAsInt();
		 int price = apdat.get("price").getAsInt();
		 
		 
		 cntStock apdat1 = new cntStock();
		 
		 appdata.setId(id);
		 appdata.setMname(mname);
		 appdata.setDescription(description);
		 appdata.setAmount(amount);
		 appdata.setPrice(price);
		 
		 
		String output = apdat1.updatestock(appdata);
		return output;
		}
		
		//Delete API
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletesto(String appobject){
		
		JsonObject apdat = new JsonParser().parse(appobject).getAsJsonObject();
		
		int id = apdat.get("id").getAsInt();

		cntStock apdat2 = new cntStock();
		appdata.setId(id);
		
		 String output = apdat2.deletestock(appdata);
		return output;
		}

	
	
	
	
		
}



