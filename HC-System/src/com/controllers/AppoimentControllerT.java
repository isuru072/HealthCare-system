package com.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.java.Payment;


@Path("/getAppoiment")
public class AppoimentControllerT {
	PaymentController appdata = new PaymentController();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Payment> getAppoiment()
	{
		System.out.println("Appoiments from DB...");
		return appdata.getAppoiment();
	}
	
	@GET
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Payment getAppoiment(@PathParam("uid") int uid)
	{
		return appdata.getAppoiment(uid);
	
	}
		
	
		
}
