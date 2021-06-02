package Area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class Zone extends Area {
	private HashMap<String,Building> listBuildings;

	public Zone(int area_id, String name) {
		super(area_id, ID.ZONE, name);
		this.listBuildings = new HashMap<>();
	}
	
	public void addBuilding(Building building) {
		this.listBuildings.put(building.getName(), building);
	}
	
	public Building getBuildingById(int id) {
		
		for(Entry<String, Building> b: this.listBuildings.entrySet()) {
            if(b.getValue().getId() == id) {
            	return b.getValue();
            }
        } 
		return null;
	}
	
	public Building getBuildingByName(String building_name) {
		return this.listBuildings.get(building_name);
	}
	
	public ArrayList<Building> getBuildings(){
		ArrayList<Building> buildings = new ArrayList<>();
		
		for(Entry<String, Building> b: this.listBuildings.entrySet()) {
            buildings.add(b.getValue());
        }
		return buildings;
	}
	
	public void createNewBuilding(String building_name) {
		this.listBuildings.put(building_name, new Building(this.listBuildings.size() + 1, this, building_name));
	}
	
	public String getInfo() {
		String out = "";
		out += "Zone ID : " + this.id + "\n";
		
		for(Entry<String, Building> b: this.listBuildings.entrySet()) {
			out += b.getValue().toString();
            out += "\n";
        } 
		
		return out;
	}
}
