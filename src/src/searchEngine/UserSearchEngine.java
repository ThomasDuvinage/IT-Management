package searchEngine;

import java.util.ArrayList;

import User.User;

public class UserSearchEngine {
	private ArrayList<User> userDB;
	
	public UserSearchEngine() {
		this.userDB = new ArrayList<User>();
	}
	
	public UserSearchEngine(ArrayList<User> DB) {
		this.userDB = DB;
	}
	
	public ArrayList<User> getUserDb() {
		return this.userDB;
	}
	
	public void addUser(User u) {
		this.userDB.add(u);
	}
	
	public boolean checkConnect(String name_id, String pwd) {
		for(User u : this.userDB) {
			if((u.getName().equals(name_id) || u.getPseudo().equals(name_id)) && u.getPWD().contentEquals(pwd))
				return true;
		}
		
		return false;
	}


}
