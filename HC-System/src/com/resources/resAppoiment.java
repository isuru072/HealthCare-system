package com.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.controller.cntAppoiment;
import com.java.appoiment;


@Path("/getAppoiment")
public class resAppoiment {
	cntAppoiment appdata = new cntAppoiment();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<appoiment> getAppoiment()
	{
		System.out.println("Appoiments from DB...");
		return appdata.getAppoiment();
	}
		
	
		
}
