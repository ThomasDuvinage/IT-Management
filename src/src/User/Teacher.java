package User;


/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * @brief This class is used to define every Teacher of the platform
 *
 */
public class Teacher extends User {
	public Teacher(String name, String first_name, String new_pwd, String new_pseudo, Departement dep) {
		super(name, first_name, new_pwd, new_pseudo);
		this.access_right = Rights.LIMITED_ACCESS;
		this.departement = dep;
		// TODO Auto-generated constructor stub
	}

	public Teacher(String name, String first_name) {
		super(name, first_name); // TODO Auto-generated constructor stub
		this.access_right = Rights.LIMITED_ACCESS;
	}

}
