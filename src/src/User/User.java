package User;

import java.util.List;

import Component.ComputerStation;
import application.Booking;

/**
 * @brief This enumeration is used to define the right associated to each user
 *
 */
enum Rights {
	FULL_ACCESS, LIMITED_ACCESS
}


/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * @brief This class is used to define every user of the platform
 *
 */

public class User {
	private String name;
	private String firstName;

	private String pseudo;
	private String pwd;
	
	private ComputerStation inUseCS;

	protected Rights access_right;
	protected Departement departement;

	public User(String name, String first_name) {
		this.name = name;
		this.firstName = first_name;
	}

	public User(String name, String first_name, String new_pseudo, String new_pwd) {
		this.name = name;
		this.firstName = first_name;
		this.pwd = new_pwd;
		this.pseudo = new_pseudo;
	}
	
	public User(String name, String first_name, String new_pseudo, String new_pwd, Departement dep ) {
		this.name = name;
		this.firstName = first_name;
		this.pwd = new_pwd;
		this.pseudo = new_pseudo;
		this.departement = dep;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String new_name) {
		this.name = new_name;
		
	}
	
	public void setFirstName(String new_first_name) {
		this.firstName = new_first_name;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getPseudo() {
		return this.pseudo;
	}
	
	public String getPWD() {
		return this.pwd;
	}
	
	public void setDepartement(Departement dep) {
		this.departement = dep;
	}
	
	public Departement getDepartement() {
		return this.departement;
	}

	public void setPWD(String new_pwd) {
		this.pwd = new_pwd;
	}

	public void setPseudo(String new_pseudo) {
		this.pseudo = new_pseudo;
	}

	public boolean isAdmin() {
		if (this.access_right == Rights.FULL_ACCESS) {
			return true;
		}

		return false;
	}

	public boolean isTeacher() {
		if (access_right == Rights.LIMITED_ACCESS) {
			return true;
		}

		return false;
	}
	
	public void use(ComputerStation cs) {
		this.inUseCS = cs;
		this.inUseCS.inUSE(this);
	}
	
	public void deconnection() {
		this.inUseCS.free();
	}
	
	public String getInfo() {
		String out = "";

		out += "\tUserType : " + this.access_right.toString() + "\n";
		out += "\tName : " + this.name + "\t First Name : " + this.firstName + "\n";
		out += "\tPseudo : " + this.pseudo + "\n";

		return out;
	}

	public String toString() {
		return this.name;
	}

}
