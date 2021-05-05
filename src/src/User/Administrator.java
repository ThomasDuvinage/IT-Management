package User;

/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * @brief This class is used to define every Administrator of the platform
 *
 */
public class Administrator extends User {

	public Administrator(String name, String first_name, String new_pwd, String new_pseudo) {
		super(name, first_name, new_pwd, new_pseudo);
		this.access_right = Rights.FULL_ACCESS;
		// TODO Auto-generated constructor stub
	}

	public Administrator(String name, String first_name) {
		super(name, first_name); // TODO Auto-generated constructor stub
		this.access_right = Rights.FULL_ACCESS;
	}

}
