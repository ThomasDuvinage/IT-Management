package Area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class Building extends Area {
	private HashMap<String, Classroom> listClassroom;
	private Zone parentZone;

	public Building(int area_id, Zone parentZone, String building_name) {
		super(area_id, ID.BAT, building_name);
		this.listClassroom = new HashMap<>();
		this.parentZone = parentZone;
		this.parentZone.addBuilding(this);
	}
	
	public void createNClassroom(int N) {
		for(int i = 0; i < N;i++) {
			this.listClassroom.put("Classroom"+i, new Classroom(this.listClassroom.size(),this, "Classroom"+i));
		}
	}
	
	public void createNClassroomWithNComputerStation(int n_class, int n_cs) {
		Classroom classroom;
		
		for(int i = 0; i < n_class;i++) {
			classroom = new Classroom(this.listClassroom.size(),this, "Classroom"+i);
			classroom.createNComputerStation(n_cs);
			this.listClassroom.put("Classroom"+i, classroom);
		}
	}

	
	public void createNewClassroom(String name) {
		this.listClassroom.put(name, new Classroom(this.listClassroom.size(),this, name));
	}
	
	public void addClassroom(Classroom room) {
		this.listClassroom.put(room.getName(), room);
	}
	
	public Classroom getClassroomByName(String name) {
		return listClassroom.get(name);
	}
	
	public Classroom getClassroomById(int id) {
		
		for( Entry<String, Classroom> room: this.listClassroom.entrySet()) {
            if(room.getValue().getId() == id) {
            	return room.getValue();
            }
        } 
		
		return null;
	}
	
	public List<Classroom> getAllClassrooms(){
		List<Classroom> listClass = new ArrayList<>();
		
		for( Entry<String, Classroom> room: this.listClassroom.entrySet()) {
            listClass.add(room.getValue());
        } 
				
		return listClass;
	}
	
	public Zone getParentZone() {
		return this.parentZone;
	}
	
	public String getInfo() {
		String out = "";
		out += "Building ID : " + this.id + "\n";
		
		for( Entry<String, Classroom> room: this.listClassroom.entrySet()) {
            out += room.getValue().toString();
            out += "\n"; 
        }
		
		return out;
	}

}
