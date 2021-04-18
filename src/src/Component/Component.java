package Component;

/**
 * @brief State of the component 
 *
 */
enum State{
	GOOD,
	TO_BE_REPARED,
	HS
}

/**
 * @brief Accessibility of the component 
 *
 */
enum Availability{
	FREE,
	IN_USE
}

/**
 * @brief Type of the component 
 *
 */
enum ComponentType{
	MOUSE,
	SCREEN,
	KEYBOARD,
	SYSTEM_UNIT
}


/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * @brief This class is used to define every Component of each UTBM's classrooms
 *
 */
public class Component {
	public String id; // TODO build depending on the building, classroom name and number of computer in the classroom
	public State state;
	
	protected Availability access;
	protected ComponentType type; // TODO set it in each component child constructor 
	
	
	public Component() {
		this.state = State.GOOD; //TODO set 
		this.access = Availability.FREE; //TODO set 
		this.id = "to be build";
	}
	
	public void setAvailability(Availability a) {
		this.access = a;
	}
	
	public Availability getAvailability() {
		return this.access;
	}
	
	public String toString() {
		String out = "";
		out += "\tComponent ID : " + this.id + "\n";
		out += "\t\tType : " + this.type.toString() + "\n";
		out += "\t\tState : " + this.state.toString() + "\n";
		out += "\t\tAvailability : " + this.access.toString() + "\n";
		
		return out;
	}

}
