package searchEngine;

import java.util.ArrayList;

import Area.Building;
import Area.Classroom;
import Component.ComputerStation;

/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * TODO add methods to fit queries which will be made in the app
 *
 */

public class SearchEngine {
	private ArrayList<Building> arrayArea;
	
	public SearchEngine(ArrayList<Building> bats) {
		this.arrayArea = bats;
	}
	
	public ArrayList<Classroom> getAllClassrooms(){
		ArrayList<Classroom> r = new ArrayList<Classroom>();
		
		for (Building b : this.arrayArea) {
			r.addAll(b.getClassrooms());
		}
		
		return r;
	}
	
	public Classroom getClassroom(int id_bat, int id_classroom) {
		return this.arrayArea.get(id_bat).getClassroomById(id_classroom);
	}
	
	public ComputerStation getComputerStation(int id_bat, int id_classroom, int id_cs) {
		return this.arrayArea.get(id_bat).getClassroomById(id_classroom).getComputerStationById(id_cs);
	}
	
	public ArrayList<Classroom> getAllClassroomWithId(int id_classroom){
		ArrayList<Classroom> r = new ArrayList<Classroom>();
		
		for (Building d : this.arrayArea) {
			r.add(d.getClassroomById(id_classroom));
		}
		
		return r;
	}
	
	public ComputerStation getComputerStationInClass(int id_building, int id_classroom, int id_cs){
		return this.arrayArea.get(id_building).getClassroomById(id_classroom).getComputerStationById(id_cs);
	}
	
	public ArrayList<ComputerStation> getInUseComputerStation(){
		ArrayList<ComputerStation> inUseCS = new ArrayList<ComputerStation>();
		
		for (Building d : this.arrayArea) {
			for(Classroom c : d.getClassrooms()) {
				inUseCS.addAll(c.getInUseComputerStation());
			}
		}
		
		return inUseCS;
	}
}
