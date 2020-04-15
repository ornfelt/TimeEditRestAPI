package com.dajo.roomrest;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Jonas Ornfelt, Daniel Arnesson
 * Room object
 *
 */
public class Reservation {
	
	private int id;
	private String starttime;
	private String startdate;
	private String endtime;
	private String enddate;
	private String[] columns;
	//name variable is set manually
	private String[] name;
	//boolean that reveals if the reservation contains only one room or not
	private boolean soloRoom = false;
	
	public Reservation(int id, String starttime, String startdate, String endtime, String enddate, String[] columns) {
		super();
		this.id = id;
		this.starttime = starttime;
		this.startdate = startdate;
		this.endtime = endtime;
		this.enddate = enddate;
		this.columns = columns;
	}
	
	//no args constructor
	public Reservation() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartTime() {
		return starttime;
	}
	public void setStartTime(String startTime) {
		this.starttime = startTime;
	}
	public String getStartDate() {
		return startdate;
	}
	public void setStartDate(String startDate) {
		this.startdate = startDate;
	}
	public String getEndTime() {
		return endtime;
	}
	public void setEndTime(String endTime) {
		this.endtime = endTime;
	}
	public String getEndDate() {
		return enddate;
	}
	public void setEndDate(String endDate) {
		this.enddate = endDate;
	}
	public String[] getColumns() {
		return columns;
	}
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	public String[] getName() {
		return name;
	}
	public void setName() {
		String[] roomNames = {"C11", "C13", "C15", "Flundran", "Rauken", "Änget", "Ã„nget", "Backsippan", "Heden", "Myren"};
		int roomsAmount = 0;
		//column[0] contains all values and needs to be changed to an actual array
		this.columns = this.columns[0].split(", ");
		
		try {
			//counts amount of different rooms found
			for(int i = 0; i < this.columns.length; i++) {
				for(int j = 0; j < roomNames.length; j++) {
					
					if(columns[i].equals(roomNames[j])) {
						roomsAmount++;
					}
				}
			}
			
			//sets new string array that will contain names found
			String[] names = new String[roomsAmount];
			int namesCounter = 0;
			
			//add names found from roomNames to the new name-array
			for(int i = 0; i < this.columns.length; i++) {
				for(int j = 0; j < roomNames.length; j++) {
					
					if(this.columns[i].equals(roomNames[j])) {
						if(roomNames[j] == "Ã„nget") {
							//set name to correct spelled version instead
							names[namesCounter] = roomNames[j-1];
						}else {
						names[namesCounter] = roomNames[j];
						}
						namesCounter++;
					}
				}
			}
			
			//sets values
			if(roomsAmount == 1)soloRoom = true;
			this.name = names;
		}
		catch(NullPointerException e) {
			System.out.println("Null pointer when setting name.");
		}
	}
	
	//toString method 
	@Override
	public String toString() {
		return "Room [id=" + id + ", startTime=" + starttime + ", startDate=" + startdate + ", endTime=" + endtime
				+ ", endDate=" + enddate + ", columns=" + Arrays.toString(columns) + ", name="+ Arrays.toString(name) + ", soloRoom=" + soloRoom + "]";
	}
}
