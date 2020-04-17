package com.dajo.roomrest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * 
 * @author Jonas Ornfelt, Daniel Arnesson
 * This is a repository for reservation objects. 
 * Currently without a real database.
 *
 */

public class ReservationRepository {

	List<Reservation> reservations;
	List<Reservation> specificReservations;

	/**
	 * Makes a Get Request @TimeEdit to fetch all reservations done at Campus Gotland.
	 * Parses the response from JSON to Java objects
	 * Adds the rooms to the list rooms
	 */
	public ReservationRepository() {
		reservations = new ArrayList<Reservation>();
		String requestUrl = "https://cloud.timeedit.net/uu/web/schema/ri.json?h=f&sid2006&objects=2547815.211%2C2547817.211%2C2547818.211%2C2547820.211%2C2547821.211%2C3654145.211%2C3654146.211%2C2547823.211%2C2547822.211&ox=0&types=0&fe=0&fw=t&p=0.m%2C36.w&h2=2";
		  
		HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .GET()
	                .uri(URI.create(requestUrl))
	                .build();
	        HttpResponse<String> response;
			try {
				// Sends the request
				response = client.send(request, HttpResponse.BodyHandlers.ofString());
				// Parses the response to jsonArray
		        JsonArray jsonArray = parseJson(response.body());
		        // Add all reservations to reservationList
				fillList(jsonArray);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Parses the response to JsonObject to JsonArray
	 * @return jsonArray
	 */
	private JsonArray parseJson(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
		JsonArray jsonArray = jsonObject.getAsJsonArray("reservations");
		return jsonArray;
	}
	
	/**
	 * Fills the list with Java Objects 
	 */
	private void fillList(JsonArray jsonArray) {
		Gson gson = new Gson();
		String jsonString = jsonArray.toString();
        
		Type listType = new TypeToken<ArrayList<Reservation>>(){}.getType();
		List<Reservation> roomList= gson.fromJson(jsonString, listType);
		
		for (Reservation r : roomList) {
			reservations.add(r);
			r.setName();
			System.out.println(r);
		}
			
	}


	/**
	 * Get list of all the reservations
	 */
	public List<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * Get room by booking-id
	 */
	public Reservation getReservations(int id) {
		// Search list for specific room
		for (Reservation r : reservations) {
			if (r.getId() == id) {
				return r;
			}
		}
		String[] tempString = { "No", "Reservation", "Found" };
		// Return new room if no room is found with given id
		return new Reservation(id, "01", "02", "03", "04", tempString);
	}

	/**
	 * Method which returns bookings with given room name
	 */
	public List<Reservation> getRoomName(String name) {
		specificReservations = new ArrayList<Reservation>();
		try {
			for (Reservation r : reservations) {
				for(int i = 0; i < r.getName().length; i++) {
					if (r.getName()[i].toLowerCase().equals(name.toLowerCase())) {
						specificReservations.add(r);
					}else if(r.getName()[i].toLowerCase().equals(",,änget") && name.toLowerCase().equals(",,änget")){
						specificReservations.add(r);
					}
				}
			}
		} catch (NullPointerException e) {
			// Test reservations with no name will cast exception
			System.out.println("Nullpointer when getting bookings with specific name.");
		}
		return specificReservations;
	}
}
