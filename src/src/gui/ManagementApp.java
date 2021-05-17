package gui;

import java.util.ArrayList;

import Area.Building;
import User.Administrator;
import User.Teacher;
import User.User;
import searchEngine.SearchEngine;
import searchEngine.UserSearchEngine;

/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * @brief Main class of the project used to launch the application
 *
 */

public class ManagementApp {
	private UserSearchEngine users;
		
	private ArrayList<Building> buildings;
	private SearchEngine engine;
	
	public ManagementApp() {
		this.users = new UserSearchEngine();
		this.users.addUser(new Administrator("toto", "roro", "admin", "admin"));
		this.users.addUser(new Teacher("hello", "world", "test", "test"));
		
		Connexion con = new Connexion();
		con.setUserSearchEngine(users);
		
		this.buildings = new ArrayList<Building>();
		this.buildings.add(new Building(0));
		this.buildings.add(new Building(1));
		
		for(Building b: this.buildings) {
			b.createNewClassroomWithCS(1);
		}
		
		for(User u : this.users.getUserDb()) {
			System.out.println(u.toString());
		}
		
		System.out.println(this.buildings.toString());
		
		this.engine = new SearchEngine(buildings);
		
		System.out.println("Search all classroom in all building");
		System.out.println(this.engine.getAllClassrooms().toString());
		
		//this.users.get(1).use(this.engine.getComputerStationInClass(0, 0, 0));
		
		System.out.println("Search all in use computerStation");
		System.out.println(this.engine.getAllClassroomWithId(0).toString());
		
		System.out.println("-- Waiting for connexion --");
		
	}
	
	public static void main(String[] args) {
		new ManagementApp();
	}
}
