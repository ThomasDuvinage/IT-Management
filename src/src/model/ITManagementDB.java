package model;

import java.util.HashMap;

import Area.Zone;

/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * TODO add methods to fit queries which will be made in the app
 *
 */

public class ITManagementDB {
	private HashMap<String, Zone> arrayZone;
	
	public ITManagementDB() {
		this.arrayZone = new HashMap<>();
	}
	
	public void addZone(Zone new_zone) {
		this.arrayZone.put(new_zone.getName(), new_zone);
	}
	
	public void createZone(String zone_name) {
		this.arrayZone.put(zone_name, new Zone(this.arrayZone.size()+1,zone_name));
	}
	
	public Zone getZoneByName(String zone_name) {
		return this.arrayZone.get(zone_name);
	}
	
	public void createBuildingIntoZone(String zone_name, String building_name) {
		
	}
}
