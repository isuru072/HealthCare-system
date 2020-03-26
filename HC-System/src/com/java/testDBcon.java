package com.java;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.resources.connect;

@Path("/dbcon")
public class testDBcon {
	@GET@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public void getCon() {
		connect testCon = new connect();
		System.out.println(testCon.connectMethod());
	}
	
}
