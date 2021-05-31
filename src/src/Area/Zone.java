package Area;

import java.util.ArrayList;


public class Zone extends Area {
	private ArrayList<Building> ListBuilding;

	public Zone(int area_id) {
		super(area_id, ID.BAT);
		this.ListBuilding = new ArrayList<Building>();
	}
	
	public void addBuilding(Building build) {
		this.ListBuilding.add(build);
	}
	
	public Building getBuildingById(int id) {
		
		for( Building build : this.ListBuilding ) {
            if(build.id == id) {
            	return build;
            }
        } 
		return null;
	}
	
	public ArrayList<Building> getBuildings(){
		return this.ListBuilding;
	}
	
	public void createNewBuildingWithCR(int nb_CR) {
		this.createNewBuilding();
		this.ListBuilding.get(this.ListBuilding.size()-1).createNewClassroom(nb_CR);
	}
	
	public void createNewBuilding() {
		this.ListBuilding.add(new Building(this.ListBuilding.size(), this));
	}
	
	public void createNewBuilding(int nb_building) {
		for(int i = 0; i < nb_building;i++) {
			this.ListBuilding.add(new Building(this.ListBuilding.size(), this));
		}
	}
	
	public String toString() {
		String out = "";
		out += "Zone ID : " + this.id + "\n";
		
		for( Building build : this.ListBuilding ) {
            out += build.toString();
            out += "\n";
        } 
		
		return out;
	}

}
