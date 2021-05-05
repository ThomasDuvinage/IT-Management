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
	private String id; // TODO build depending on the building, classroom name and number of computer in the classroom
	public State state;
	
	protected ComponentType type; // TODO set it in each component child constructor 
	
	public Component(int n_id) {
		this.state = State.GOOD; //TODO set 
		this.id = Integer.toString(n_id); // TODO Bat(A/B/C/...)-102-(CS/K/M/S/SU)102
	}
	
	public String getBat() {
		return this.id.split("-")[0];
	}
	
	public String getClassroom() {
		return this.id.split("-")[1];
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String new_id) {
		this.id = new_id;
	}
	
	
	public String toString() {
		String out = "";
		out += "\tComponent ID : " + this.id + "\n";
		out += "\t\tType : " + this.type.toString() + "\n";
		out += "\t\tState : " + this.state.toString() + "\n";
		
		return out;
	}

}
