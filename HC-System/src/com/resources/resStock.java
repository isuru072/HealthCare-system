package com.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.controller.cntStock;
import com.java.stock;


@Path("/getStock")
public class resStock {
	
	cntStock appdata = new cntStock();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<stock> getStock()
	{
		System.out.println("Stocks from DB...");
		return appdata.getStock();
	}
		
	
		
}



