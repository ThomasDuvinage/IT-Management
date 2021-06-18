package Component;

import Area.Classroom;

/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * @brief This class is used to define every Component of each UTBM's classrooms
 *
 */
public abstract class Component {
	private String name;
	protected int id;
	
	protected State state;
	protected ComponentType type; // TODO set it in each component child constructor 
	protected Availability access;
	
	private ComputerStation parentComputerStation;
	private Classroom parentClassroom;
	
	public Component(int n_id) {
		this.state = State.GOOD; //TODO set 
		this.access = Availability.FREE;
		this.id = n_id;
	}
	
	public Component(int n_id, Classroom parent) {
		this.state = State.GOOD; //TODO set 
		this.access = Availability.FREE;
		this.id = n_id;
		this.parentClassroom = parent;
	}
	
	public ComputerStation getParentComputerStation() {
		return this.parentComputerStation;
	}
	
	public void setParentComputerStation(ComputerStation parentCS) {
		this.parentComputerStation = parentCS;
	}
	
	public Classroom getParentClassroom() {
		return this.parentClassroom;
	}
	
	public void setParentClassroom(Classroom room) {
		this.parentClassroom = room;
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
	
	abstract public String getImage(int size);
	
	public String toString() {
		String out = "";
		out += "\tComponent ID : " + this.id + "\n";
		out += "\t\tType : " + this.type.toString() + "\n";
		out += "\t\tState : " + this.state.toString() + "\n";
		
		return out;
	}

}
