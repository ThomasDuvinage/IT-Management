package User;

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
	public String name;
	public String firstName;

	private String pseudo;
	private String pwd;

	protected Rights access_right;

	public User(String name, String first_name) {
		this.name = name;
		this.firstName = first_name;
	}

	public User(String name, String first_name, String new_pwd, String new_pseudo) {
		this.name = name;
		this.firstName = first_name;
		this.pwd = new_pwd;
		this.pseudo = new_pseudo;
	}

	protected void setPWD(String new_pwd) {
		this.pwd = new_pwd;
	}

	protected void setPseudo(String new_pseudo) {
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

	public String toString() {
		String out = "";

		out += "UserType : " + this.access_right.toString() + "\n";
		out += "Name : " + this.name + "\t First Name : " + this.firstName + "\n";
		out += "Pseudo : " + this.pseudo + "\n";

		return out;

	}

}
