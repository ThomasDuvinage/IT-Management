package gui;

import Area.Building;
import Component.ComputerStation;
import Component.Keyboard;
import Component.Mouse;
import Component.Screen;
import Component.SystemUnit;
import User.Administrator;
import User.Teacher;
import User.User;

/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * @brief Main class of the project used to launch the application
 *
 */

public class ManagementApp {
	private User admin, teacher;//TODO I think the best thing to do here is to define an array of Users
	
	private ComputerStation station1;//TODO define building and classroom and then add classrooms in building and ComputerStation in classrooms
	
	private Building BatB;
	
	
	public ManagementApp() {
		this.admin = new Administrator("toto", "roro");
		this.teacher = new Teacher("hello", "world", "test", "test");
		
		this.BatB = new Building(0);
		this.BatB.createNewClassroomWithCS(0,10);
		
		System.out.println(admin.toString());
		System.out.println(teacher.toString());
		System.out.println(this.BatB.toString());
	}
	
	public static void main(String[] args) {
		new ManagementApp();
	}
}
