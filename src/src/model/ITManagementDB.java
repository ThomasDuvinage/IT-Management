package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Area.Building;
import Area.Classroom;
import Area.Zone;
import Component.Component;
import Component.ComponentType;
import Component.ComputerStation;
import Component.State;
import User.User;

/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 *         TODO add methods to fit queries which will be made in the app
 *
 */

public class ITManagementDB {
	private HashMap<String, Zone> listZone;
	private ArrayList<Booking> listBooking;

	public ITManagementDB() {
		this.listZone = new HashMap<>();
		this.listBooking = new ArrayList<Booking>();
	}

	public void addZone(Zone new_zone) {
		this.listZone.put(new_zone.getName(), new_zone);
	}
	
	public void addBooking(Booking booking) {
		this.listBooking.add(booking);
	}

	public void createZone(String zone_name) {
		this.listZone.put(zone_name, new Zone(this.listZone.size() + 1, zone_name));
	}
	
	public List<Booking> getUserBookings(User user) {
		List<Booking> user_bookings = new ArrayList<Booking>();
		for (Booking booking : this.listBooking) {
			if (booking.getUser() == user) {
				user_bookings.add(booking);
			}
		}
		return user_bookings;
	}

	public Zone getZoneByName(String zone_name) {
		return this.listZone.get(zone_name);
	}

	public List<Building> getBuildingByZone(String zone_name) {
		return this.listZone.get(zone_name).getAllBuildings();
	}

	public List<Classroom> getClassroomsByBuildingName(String zone_name, String building_name) {
		return this.listZone.get(zone_name).getBuildingByName(building_name).getAllClassrooms();
	}

	public List<Classroom> getClassroomsByZone(String zone_name) {
		List<Classroom> classrooms = new ArrayList<>();
		for (Building building : this.getZoneByName(zone_name).getAllBuildings()) {
			classrooms.addAll(building.getAllClassrooms());
		}
		return classrooms;
	}

	public List<ComputerStation> getComputerStationByZone(String zone_name) {
		List<ComputerStation> computer_stations = new ArrayList<>();

		for (Classroom classroom : this.getClassroomsByZone(zone_name)) {
			computer_stations.addAll(classroom.getAllComputerStations());
		}

		return computer_stations;
	}

	public List<ComputerStation> getComputerStationByBuilding(String zone_name, String building_name) {
		List<ComputerStation> computer_stations = new ArrayList<>();

		for (Classroom classroom : this.getClassroomsByBuildingName(zone_name, building_name)) {
			computer_stations.addAll(classroom.getAllComputerStations());
		}

		return computer_stations;
	}

	public List<ComputerStation> getComputerStationByClassroom(String zone_name, String building_name,
			String classroom_name) {
		return this.listZone.get(zone_name).getBuildingByName(building_name).getClassroomByName(classroom_name)
				.getAllComputerStations();
	}
	
	public ComputerStation getComputerStation(String zone_name, String building_name,
			String classroom_name, int cs_id) {
		return this.listZone.get(zone_name).getBuildingByName(building_name).getClassroomByName(classroom_name)
				.getComputerStationById(cs_id);
	}
	
	public void removeComputerStationAt(String zone_name, String building_name, String classroom_name, int cs_id) {
		this.listZone.get(zone_name).getBuildingByName(building_name).getClassroomByName(classroom_name).deleteComputerStation(cs_id);
	}
	
	public void moveComputerStationTo(String zone_name, String building_name, String classroom_name, ComputerStation cs) {
		cs.getParentClassroom().deleteComputerStation(cs.getId());
		this.listZone.get(zone_name).getBuildingByName(building_name).getClassroomByName(classroom_name).addComputerStation(cs);
	}

	public List<ComputerStation> getAllComputerStations() {
		List<ComputerStation> computer_stations = new ArrayList<>();

		for (Entry<String, Zone> zone : this.listZone.entrySet()) {
			for (Building building : zone.getValue().getAllBuildings()) {
				for (Classroom c : building.getAllClassrooms()) {
					computer_stations.addAll(c.getAllComputerStations());
				}
			}
		}

		return computer_stations;
	}
	
	public void addComputerStation(String zone_name, String building_name,
			String classroom_name) {
		Classroom classroom = this.listZone.get(zone_name).getBuildingByName(building_name).getClassroomByName(classroom_name);
		
		classroom.addComputerStation();
	}

	public void addComponentToComputerStation(ComponentType type, String zone_name, String building_name,
			String classroom_name, int cs_id) {
		ComputerStation cs = this.listZone.get(zone_name).getBuildingByName(building_name)
				.getClassroomByName(classroom_name).getComputerStationById(cs_id);
		
		cs.addNewComponent(type);
	}
	
	public void changeComponentState(Component cp, State new_state) {
		cp.setState(new_state);
		cp.getParentComputerStation().updateState();
	}

	public ArrayList<Zone> getAllZone() {
		ArrayList<Zone> zones = new ArrayList<>();

		for (Entry<String, Zone> zone : this.listZone.entrySet()) {
			zones.add(zone.getValue());
		}

		return zones;
	}

	public void createBuildingIntoZone(String zone_name, String building_name) {

	}
	
	public boolean isComputerStationOccupied(ComputerStation cs, LocalDate start_d, int start_h, int start_m, int end_h, int end_m) {
		boolean occupied = false;
		
		int i = listBooking.size() - 1;
		while (i >= 0 && !occupied) {
			Booking booking = listBooking.get(i);
			if (booking.getComputerStations().contains(cs)) {
				if (booking.getDate().equals(start_d)) {
					boolean possible = false;
					if (end_h*60 + end_m <= booking.getStartTime()) {
						possible = true;
					}
					if (start_h*60 + start_m >= booking.getEndTime()) {
						possible = true;
					}
					occupied = !possible;
				}
			}
			i--;
		}
		return occupied;
	}
	
	public void deleteBooking(Booking booking) {
		booking.getComputerStations().clear();
		this.listBooking.remove(booking);
	}
	
	public void deleteBookingStation(Booking booking, ComputerStation cs) {
		booking.getComputerStations().remove(cs);
		if (booking.getComputerStations().size() == 0) {
			deleteBooking(booking);
		}
	}
}
