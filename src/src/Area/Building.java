package Area;

import java.util.ArrayList;


public class Building extends Area {
	private ArrayList<Classroom> ListClassroom;
	private Zone parentZone;

	public Building(int area_id, Zone parentZone) {
		super(area_id, ID.BAT);
		this.ListClassroom = new ArrayList<Classroom>();
		this.parentZone = parentZone;
	}
	
	public void addClassroom(Classroom room) {
		this.ListClassroom.add(room);
	}
	
	public Classroom getClassroomById(int id) {
		
		for( Classroom room : this.ListClassroom ) {
            if(room.id == id) {
            	return room;
            }
        } 
		return null;
	}
	
	public ArrayList<Classroom> getClassrooms(){
		return this.ListClassroom;
	}
	
	public void createNewClassroomWithCS(int nb_CS) {
		this.createNewClassroom();
		this.ListClassroom.get(this.ListClassroom.size()-1).createNewComputerStation(nb_CS);
		
	}
	
	public void createNewClassroom() {
		this.ListClassroom.add(new Classroom(this.ListClassroom.size(), this));
	}
	
	public void createNewClassroom(int nb_classroom) {
		for(int i = 0; i < nb_classroom;i++) {
			this.ListClassroom.add(new Classroom(this.ListClassroom.size(), this));
		}
	}
	
	public String toString() {
		String out = "";
		out += "Building ID : " + this.id + "\n";
		
		for( Classroom room : this.ListClassroom ) {
            out += room.toString();
            out += "\n";
        } 
		
		return out;
	}

}
