package com.dajo.roomrest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Jonas Ornfelt, Daniel Arnesson 
 * Returns JSON or XML data about reservations, depending on request.
 *
 */

@Path("reservations")
public class ReservationResource {
	
	ReservationRepository repo = new ReservationRepository();

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Reservation> getRooms() {
    	System.out.println("getRooms called");
    	return repo.getReservations();
	}
    
    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Reservation getReservation(@PathParam("id")int id) {
    	System.out.println("getReservation called");
    	return repo.getReservations(id);
	}
    
    @GET
    @Path("room/{name}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Reservation> getReservation(@PathParam("name")String name) {
    	System.out.println("getRoomName called");
    	return repo.getRoomName(name);
	}
}
