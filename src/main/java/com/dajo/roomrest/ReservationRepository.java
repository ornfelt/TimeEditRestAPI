package com.dajo.roomrest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

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
		reservations = new ArrayList<>();

		//String requestURL = "https://cloud.timeedit.net/uu/web/schema/ri62Y693Y23062QQ96Z552690558655965336351Y855X85X896X2859865816967168XY668856735YY25X06597555YX95X132Y96Y83Q67Z65f0620f9Q6Q76Xd8nQ50ZZ785vyZ6Y55XQcto42ZY0CQ138FAF01Ct108900518087794E9170FABD1.json";
		String requestURL = "https://cloud.timeedit.net/uu/web/schema/ri.json?h=f&sid=2006&objects=2547815.211%2C2547817.211%2C2547818.211%2C2547820.211%2C2547821.211%2C3654145.211%2C3654146.211%2C2547823.211%2C2547822.211&ox=0&types=0&fe=0&fw=t&p=0.m%2C12.w&h2=2";
		// try to send get request to given url
		try {
			HttpUtility.sendGetRequest(requestURL);
			String[] response = HttpUtility.readMultipleLinesRespone();

			// no need for JsonParser, since gson can be used (jsonparser also works, but
			// will be more lines of code in the end)
			JsonArray jsonArray = parseJson(response);
			fillList(jsonArray);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		HttpUtility.disconnect();
	}
	
	/**
	 * Converts the response to JSONArray
	 */
	private JsonArray parseJson(String[] response) {
		
		JsonParser parser = new JsonParser();
		JsonElement rootElement = parser.parse(response[0]);
		JsonObject object = rootElement.getAsJsonObject();
		JsonArray jsonArray = object.getAsJsonArray("reservations");
		
		return jsonArray;
		
	}
	
	/**
	 * Fills the list with Java Objects 
	 */
	private void fillList(JsonArray jsonArray) {
		
		Gson gson = new Gson();
		String jsonString = jsonArray.toString();

		Type reservationListType = new TypeToken<ArrayList<Reservation>>() {
		}.getType();
		List<Reservation> roomList = gson.fromJson(jsonString, reservationListType);
		for (Reservation r : roomList) {
			reservations.add(r);
			r.setName();
			System.out.println(r.toString());
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
		// search list for specific room
		for (Reservation r : reservations) {
			if (r.getId() == id) {
				return r;
			}
		}
		String[] tempString = { "Test", "Booking" };
		// return new room if no room is found with given id
		return new Reservation(id, "01", "02", "03", "04", tempString);
	}

	/**
	 * Method which returns bookings with given room name
	 */
	public List<Reservation> getRoomName(String name) {
		specificReservations = new ArrayList<>();
		try {
			for (Reservation r : reservations) {
				for(int i = 0; i < r.getName().length; i++) {
					if (r.getName()[i].toLowerCase().equals(name.toLowerCase())) {
						specificReservations.add(r);
					}
				}
			}
		} catch (NullPointerException e) {
			// test reservations with no name will cast exception
			System.out.println("Nullpointer when getting bookings with specific name.");
		}
		return specificReservations;
	}
}
