package Component;

/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * @brief This class is used to define every Component of each UTBM's classrooms
 *
 */
public class Component {
	private String name;
	protected int id;
	
	protected State state;
	protected ComponentType type; // TODO set it in each component child constructor 
	protected Availability access;
	
	private ComputerStation parentComputerStation;
	
	public Component(int n_id) {
		this.state = State.GOOD; //TODO set 
		this.access = Availability.FREE;
		this.id = n_id;
	}
	
	public void setParentComputerStation(ComputerStation parentCS) {
		this.parentComputerStation = parentCS;
	}
	
	public void setState(State new_state) {
		this.state = new_state;
	}
	
	public State getState() {
		return this.state;
	}
	
	protected void setName() {
		this.name = this.type.toString() + this.id;
	}
	
	public Availability getAccess() {
		return this.access;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setId(int new_id) {
		this.id = new_id;
	}
	
	public ComponentType getType() {
		return this.type;
	}
	
	
	public String toString() {
		String out = "";
		out += "\tComponent ID : " + this.id + "\n";
		out += "\t\tType : " + this.type.toString() + "\n";
		out += "\t\tState : " + this.state.toString() + "\n";
		
		return out;
	}

}
