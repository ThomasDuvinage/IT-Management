package model;


import java.util.ArrayList;
import java.util.List;

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
	
	public ArrayList<User> getUserDB() {
		return this.users;
	}
	
	public List<User> getUserByDepartement(Departement dep){
		List<User> users_dep = new ArrayList<>();
		
		for(User u: this.users) {
			if(u.getDepartement() == dep || dep == null) {
				users_dep.add(u);
			}
		}
		
		return users_dep;
	}
	
	public void deleteUser(User user) {
		this.users.remove(user);
	}
	
	public void deleteUserAt(int index) {
		this.users.remove(index);
	}
	
	public int getUserIndexByName(String name) {
		for(int i = 0; i < this.users.size();i++) {
			if(users.get(i).getName().equals(name)) {
				return i; 
			}
		}
		return 0;
	}
	
	public User getUserByName(String name) {
		for(int i = 0; i < this.users.size();i++) {
			if(users.get(i).getName().equals(name)) {
				return users.get(i); 
			}
		}
		return null;
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
