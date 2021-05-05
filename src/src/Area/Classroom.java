package Area;

import java.util.ArrayList;

import Component.ComputerStation;

public class Classroom extends Area {
	private ArrayList<ComputerStation> ListComponents;

	public Classroom(int area_id) {
		super(area_id, ID.CLASS);
		this.ListComponents = new ArrayList<ComputerStation>();
	}
	
	
	public void addClassroom(ComputerStation cmp) {
		this.ListComponents.add(cmp);
	}
	
	public ComputerStation getClassroomById(String id) { // TODO change to int 
		
		for( ComputerStation cs : this.ListComponents ) {
            if(cs.getId() == id) {
            	return cs;
            }
        } 
		return null;
	}
	
	public void createNewComputerStation(int nb_station) {
		for(int i = 0; i < nb_station;i++) {
			this.ListComponents.add(new ComputerStation(this.ListComponents.size()));
		}
	}
	
	public String toString() {
		String out = "";
		out += "Area ID : " + this.id + "\n";
		out += "Type : " + this.type.toString() + "\n";
		
		for(ComputerStation cs : this.ListComponents) {
            out += cs.toString();
            out += "\n";
        } 
		
		return out;
	}

}
