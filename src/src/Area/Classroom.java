package Area;

import java.util.ArrayList;
import java.util.List;

import Component.Component;
import Component.ComputerStation;

public class Classroom extends Area {
	private List<ComputerStation> ListComponents;
	private Building parentBuilding;

	public Classroom(int area_id, Building parentBuilding, String class_name) {
		super(area_id, ID.CLASS, class_name);
		this.ListComponents = new ArrayList<ComputerStation>();
		this.parentBuilding = parentBuilding;
		this.parentBuilding.addClassroom(this);
	}
	
	public void addComputerStation() {
		this.ListComponents.add(new ComputerStation(this.ListComponents.size(), this));
	}
		
	public void addComputerStation(ComputerStation cmp) {
		this.ListComponents.add(cmp);
	}
	
	public ComputerStation getComputerStationById(int id) {
		
		for( ComputerStation cs : this.ListComponents ) {
            if(cs.getId() == id) {
            	return cs;
            }
        } 
		return null;
	}
	
	public void createNComputerStation(int nb_station) {
		ComputerStation cs = null;
		for(int i = 0; i < nb_station;i++) {
			this.ListComponents.add(new ComputerStation(this.ListComponents.size(), this));
		}
	}
	
	public ArrayList<ComputerStation> getInUseComputerStation(){
		ArrayList<ComputerStation> inUseCS = new ArrayList<ComputerStation>();
		
		for(ComputerStation cs : this.ListComponents) {
			if(!cs.isAvailable()) {
				inUseCS.add(cs);
			}
		}
		
		return inUseCS;
	}
	
	public List<ComputerStation> getAllComputerStations(){
		return this.ListComponents;
	}
	
	public void deleteComputerStation(int cs_id) {
		this.ListComponents.remove(cs_id);
	}
	
	public Building getParentBuilding() {
		return this.parentBuilding;
	}
	
	public String getInfo() {
		String out = "";
		out += "Area ID : " + this.id + "\n";
		out += "Parent Building ID : " + this.parentBuilding.id + "\n";
		out += "Type : " + this.type.toString() + "\n";
		
		for(ComputerStation cs : this.ListComponents) {
            out += cs.toString();
            out += "\n";
        } 
		
		return out;
	}

}
