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
public class RoomResource {
	
	RoomRepository repo = new RoomRepository();

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Room> getRooms() {
    	System.out.println("getRooms called");
    	return repo.getRooms();
	}
    
    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Room getRoom(@PathParam("id")int id) {
    	System.out.println("getRoom called");
    	return repo.getRoom(id);
	}
    
    @GET
    @Path("room/{name}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Room> getRoom(@PathParam("name")String name) {
    	System.out.println("getRoomName called");
    	return repo.getRoomName(name);
	}
}
