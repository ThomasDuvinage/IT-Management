package Area;

import java.util.ArrayList;

import Component.ComputerStation;

public class Classroom extends Area {
	private ArrayList<ComputerStation> ListComponents;
	private Building parentBuilding;

	public Classroom(int area_id, Building parentBuilding, String class_name) {
		super(area_id, ID.CLASS, class_name);
		this.ListComponents = new ArrayList<ComputerStation>();
		this.parentBuilding = parentBuilding;
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
	
	public void createNewComputerStation(int nb_station) {
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
	
	public String toString() {
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
