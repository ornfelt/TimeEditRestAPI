package com.dajo.roomrest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Jonas Ornfelt
 * This is a repository for room objects. Currently without a real database.
 *
 */

public class RoomRepository {

	List<Room> rooms;
	List<Room> specificRooms;
	
	public RoomRepository() {
		rooms = new ArrayList<>();
		
		String requestURL = "https://cloud.timeedit.net/uu/web/schema/ri12Y693Y23063QQ26Z552690558655965396351Y855X85X896X2658815856960969XY788256655YY85X76597553YX95X132Y16Y53X678656662559Q7.json";
        //try to send get request to given url
        try{
            HttpUtility.sendGetRequest(requestURL);
            String[] response = HttpUtility.readMultipleLinesRespone();
            
            //no need for JsonParser, since gson can be used (jsonparser also works, but will be more lines of code in the end)
			JsonParser parser = new JsonParser();
            JsonElement rootElement = parser.parse(response[0]);
            JsonObject object = rootElement.getAsJsonObject();
            JsonArray jsonArray = object.getAsJsonArray("reservations");
            /*
             * sout for testing (works)
            for(JsonElement ele : jsonArray) {
            	System.out.println(ele.toString());
            }
            */
            
            Gson gson = new Gson();
            String jsonString = jsonArray.toString();

            Type roomListType = new TypeToken<ArrayList<Room>>(){}.getType();
            List<Room> roomList = gson.fromJson(jsonString, roomListType);
            for(Room r : roomList) {
            	rooms.add(r);
            	r.setName();
            	System.out.println(r.toString());
            }
            
        } catch(IOException ex){
            ex.printStackTrace();
        }
        HttpUtility.disconnect();
		String[] tempString = {"Test", "booking"};
        
		//two test rooms
		Room room = new Room(1, "01", "01", "01", "01", tempString);
		Room room2 = new Room(1, "02", "02", "02", "02", tempString);
		
		rooms.add(room);
		rooms.add(room2);
	}
	
	//get list of rooms
	public List<Room> getRooms(){
		return rooms;
	}
	
	//get room by id
	public Room getRoom(int id) {
		//search list for specific room
		for(Room r : rooms) {
			if(r.getId() == id) {
				return r;
			}
		}
		String[] tempString = {"Test", "Booking"};
		//return new room if no room is found with given id
		return new Room(id, "01", "02", "03", "04", tempString);
	}
	
	//add new room
	public void create(Room r) {
		rooms.add(r);
	}
	
	//method that returns bookings with given room name
	public List<Room> getRoomName(String name) {
		specificRooms = new ArrayList<>();
		try {
		for(Room r : rooms) {
			if(r.getName().equals(name)) {
				specificRooms.add(r);
			}
		}
		}catch(NullPointerException e) {
			//test rooms/bookings with no name will cast exception
			System.out.println("Nullpointer when getting bookings with specific name.");
		}
		return specificRooms;
	}
}
