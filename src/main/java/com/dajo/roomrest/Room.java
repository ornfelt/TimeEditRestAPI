package com.dajo.roomrest;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

public class Room {
	
	private int id;
	private String starttime;
	private String startdate;
	private String endtime;
	private String enddate;
	private String[] columns;
	//name variable is set manually
	private String name;
	
	public Room(int id, String starttime, String startdate, String endtime, String enddate, String[] columns) {
		super();
		this.id = id;
		this.starttime = starttime;
		this.startdate = startdate;
		this.endtime = endtime;
		this.enddate = enddate;
		this.columns = columns;
	}
	
	//no args constructor
	public Room() {}
	
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
	public String getName() {
		return name;
	}
	public void setName() {
		try {
			//hard coded solution to fix spelling error. Justified since no general solution is needed
			if(columns[4].equals("Ã„nget")) {
				this.name = "Änget";
			} else if(columns[4] == null) {
				this.name = "Inget namn hittades";
			}
			else {
				this.name = columns[4];
			}
		}
		catch(NullPointerException e) {
			System.out.println("Null pointer when setting name");
		}
	}
	
	//toString method 
	@Override
	public String toString() {
		return "Room [id=" + id + ", startTime=" + starttime + ", startDate=" + startdate + ", endTime=" + endtime
				+ ", endDate=" + enddate + ", columns=" + Arrays.toString(columns) + ", name="+ name + "]";
	}
}
