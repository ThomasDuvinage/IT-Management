package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Component.ComputerStation;
import User.User;

public class Booking {
	
	private String name;
	
	private User user;
	private ArrayList<ComputerStation> stations;
	
	private LocalDate date;
	private int startHour;
	private int startMinute;
	
	private int endHour;
	private int endMinute;
	
	public Booking(User u, ComputerStation cs, LocalDate start_d, int start_h, int start_m, int end_h, int end_m) {
		this.user = u;
		this.stations = new ArrayList<ComputerStation>();
		this.stations.add(cs);
		
		this.date = start_d;
		this.startHour = start_h;
		this.startMinute = start_m;
		
		this.endHour = end_h;
		this.endMinute = end_m;
		
		this.name = start_d.toString() + ": " + start_h + "h" + start_m + " - " + end_h + "h" + end_m;
	}
	
	public void addComputerStation(ComputerStation cs) {
		this.stations.add(cs);
	}
	
	public String getName() {
		return this.name;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public List<ComputerStation> getComputerStations() {
		return this.stations;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public int getStartHour() {
		return this.startHour;
	}
	
	public int getStartMinute() {
		return this.startMinute;
	}
	
	public int getEndHour() {
		return this.endHour;
	}
	
	public int getEnMinute() {
		return this.endMinute;
	}
	
	public int getStartTime() {
		return this.startHour*60 + this.startMinute;
	}
	
	public int getEndTime() {
		return this.endHour*60 + this.endMinute;
	}
	
	public boolean isSameBooking(LocalDate start_d, int start_h, int start_m, int end_h, int end_m) {
		return (start_d.equals(date) && start_h == startHour && start_m == startMinute && end_h == endHour && end_m == endMinute);
	}
	
	public String toString() {
		return this.getName();
	}
}
