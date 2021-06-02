package model;

import java.util.ArrayList;

import User.Administrator;
import User.Departement;
import User.Teacher;
import User.User;

public class UserDB {
	private ArrayList<User> users;
	
	public UserDB() {
		this.users = new ArrayList<User>();
		
		//TODO the following are here just for the test they have to be removed !! 
		addUser(new Administrator("admin", "admin", "admin", "admin"));
		addUser(new Teacher("teacher", "teacher", "teacher", "teacher", Departement.INFO));
	}
	
	public UserDB(ArrayList<User> DB) {
		this.users = DB;
	}
	
	public ArrayList<User> getUserDb() {
		return this.users;
	}
	
	public void addUser(User u) {
		this.users.add(u);
	}
	
	public User getUserByNamePwd(String name_id, String pwd) {	
		User user = null;
		for(User u : this.users) {
			if((u.getName().equals(name_id) || u.getPseudo().equals(name_id)) && u.getPWD().contentEquals(pwd)) {
				user = u;
				break;
			}
		}
		return user;
	}
	
	public boolean checkConnect(String name_id, String pwd) {
		for(User u : this.users) {
			if((u.getName().equals(name_id) || u.getPseudo().equals(name_id)) && u.getPWD().contentEquals(pwd))
				return true;
		}
		
		return false;
	}
}
