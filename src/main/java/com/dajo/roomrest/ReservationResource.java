package com.dajo.TimeEditRestAPI;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * 
 * @author Jonas Ornfelt, Daniel Arnesson 
 * Returns JSON or XML data about reservations, depending on request.
 *
 */

@Path("")
public class ReservationResource {
	
	ReservationRepository repo;
	CacheControl cc;  
	ResponseBuilder builder;

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	
	
	public ReservationResource() {
		repo = new ReservationRepository();
		cc = new CacheControl(); 
		cc.setMaxAge(900);  
    	cc.setPrivate(true);  	
	}
	
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response
	 getReservations() {
    	List<Reservation> rList = repo.getReservations();
    	
    	builder = Response.ok(rList);  
    	builder.cacheControl(cc);  
    	System.out.println("get called");
    	return builder.build();
	}
    
    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getId(@PathParam("id")int id) {
    	
    	Reservation r = repo.getReservations(id);   
    	builder = Response.ok(r);  
    	builder.cacheControl(cc);  
    	
    	System.out.println("getReservation called");
    	return builder.build();
	}
    
    @GET
    @Path("room/{name}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getReservation(@PathParam("name")String name) {
    	
    	List<Reservation> rList = repo.getRoomName(name);
    	builder = Response.ok(rList);  
    	builder.cacheControl(cc);  
    	
    	System.out.println("getRoomName called");
    	return builder.build();
	}
}