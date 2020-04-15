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
		String[] roomNames = {"C11", "C13", "C15", "Flundran", "Rauken", "Änget", "Backsippan", "Heden", "Myren"};
		int roomsAmount = 0;
		
		try {
			//hard coded solution to fix spelling error. Justified since no general solution is needed
			/*
			
			if(columns[0].equals("Ã„nget")) {
				this.name = "Änget";
			} else if(columns[0] == null) {
				this.name = "Inget namn hittades";
			}
			else {
				this.name = columns[0];
			}
			*/
			
			for(int i = 0; i < columns.length; i++) {
				for(int j = 0; j < roomNames.length; j++) {
					
					if(columns[i].equals(roomNames[j])) {
						roomsAmount++;
					}
				}
			}
			
			if(roomsAmount > 1) {
				
			}
			
			
		}
		catch(NullPointerException e) {
			System.out.println("Null pointer when setting name.");
		}
	}
	
	//toString method 
	@Override
	public String toString() {
		return "Room [id=" + id + ", startTime=" + starttime + ", startDate=" + startdate + ", endTime=" + endtime
				+ ", endDate=" + enddate + ", columns=" + Arrays.toString(columns) + ", name="+ name[0] + "]";
	}
}
