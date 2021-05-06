package gui;

import java.util.ArrayList;

import Area.Building;
import User.Administrator;
import User.Teacher;
import User.User;
import searchEngine.SearchEngine;

/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * @brief Main class of the project used to launch the application
 *
 */

public class ManagementApp {
	private User admin, teacher;//TODO I think the best thing to do here is to define an array of Users
		
	private ArrayList<Building> buildings;
	private SearchEngine engine;
	
	public ManagementApp() {
		this.admin = new Administrator("toto", "roro");
		this.teacher = new Teacher("hello", "world", "test", "test");
		
		this.buildings = new ArrayList<Building>();
		this.buildings.add(new Building(0));
		this.buildings.add(new Building(1));
		
		for(Building b: this.buildings) {
			b.createNewClassroomWithCS(1);
		}
		
		System.out.println(admin.toString());
		System.out.println(teacher.toString());
		System.out.println(this.buildings.toString());
		
		
		this.engine = new SearchEngine(buildings);
		
		System.out.println("Search all classroom in all building");
		System.out.println(this.engine.getAllClassrooms().toString());
		
		this.teacher.use(this.engine.getComputerStationInClass(0, 0, 0));
		
		System.out.println("Search all in use computerStation");
		System.out.println(this.engine.getAllClassroomWithId(0).toString());
		
		
	}
	
	public static void main(String[] args) {
		new ManagementApp();
	}
}
