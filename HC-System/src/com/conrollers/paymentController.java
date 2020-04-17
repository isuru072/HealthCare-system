package com.conrollers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.resources.payment;
import com.service.paymentService;


@Path("/getAppoiment")
public class paymentController {
	paymentService appdata = new paymentService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<payment> getAppoiment()
	{
		System.out.println("Appoiments from DB...");
		return appdata.getAppoiment();
	}
	
	@GET
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public payment getAppoiment(@PathParam("uid") int uid)
	{
		return appdata.getAppoiment(uid);
	
	}
		
	
		
}
