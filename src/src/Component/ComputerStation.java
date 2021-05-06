package Component;

import java.util.ArrayList;

import Area.Classroom;
import User.User;

/**
 * 
 * @brief Enumeration to define the state of the computer station. The computer
 *        station's state is updated depending on its components states.
 *
 */
enum ComputerStationState {
	OUT_OF_SERVICE, IN_SERVICE
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
 * TODO
 * 
 * implement the following method : - replaceComponent(c, new_c) -
 * deleteComponen(c) ...
 */

/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * @brief This class defined a computer station made of a mouse, a screen, a
 *        keyboard, a system unit.
 *
 */

public class ComputerStation {
	private int id;
	private ComputerStationState state;
	private ArrayList<Component> ListComponent;
	private Classroom parentClassroom;
	
	private User user;
	
	protected Availability access;

	public ComputerStation(int id, Classroom parentClassroom) {
		this.id = id;
		this.ListComponent = new ArrayList<Component>();
		setComputerStation();
		this.parentClassroom = parentClassroom;
		
		this.state = ComputerStationState.IN_SERVICE;
		this.access = Availability.FREE; //TODO set 
	}
	
	private void setComputerStation() {
		this.addNewComponent(ComponentType.MOUSE);
		this.addNewComponent(ComponentType.SCREEN);
		this.addNewComponent(ComponentType.KEYBOARD);
		this.addNewComponent(ComponentType.SYSTEM_UNIT);
	}
	
	public void inUSE(User u) {
		this.user = u;
		this.access = Availability.IN_USE;
	}
	
	public void free() {
		this.user = null;
		this.access = Availability.FREE;
	}
	
	public Availability getAvailability() {
		return this.access;
	}
	
	public boolean isAvailable() {
		if(this.access == Availability.FREE) {
			return true;
		}
		return false;
	}

	public void addNewComponent(ComponentType type) {
		switch (type) {
		case MOUSE:
			this.ListComponent.add(new Mouse(this.ListComponent.size()));
			break;
		case SCREEN:
			this.ListComponent.add(new Screen(this.ListComponent.size()));
			break;
		case KEYBOARD:
			this.ListComponent.add(new Keyboard(this.ListComponent.size()));
			break;
		case SYSTEM_UNIT:
			this.ListComponent.add(new SystemUnit(this.ListComponent.size()));
			break;
		}
	}

	public void addComponent(Component c) {
		this.ListComponent.add(c);
	}
	
	
	public Classroom getParentClassroom() {
		return this.parentClassroom;
	}

	public int getId() {
		return this.id;
	}

	public void updateState() {
		for (int i = 0; i < ListComponent.size(); i++) {
			if (ListComponent.get(i).state == State.HS || ListComponent.get(i).state == State.TO_BE_REPARED) {
				this.state = ComputerStationState.OUT_OF_SERVICE;
			}
		}
	}

	public String toString() {
		String out = "";
		out += "Computer Station ID : " + this.id + "\n";
		out += "Parent Classroom ID : " + this.parentClassroom.getId() + "\n";
		out += "State : " + this.state.toString() + "\n";
		out += "Availability : " + this.access.toString() + "\n";
		
		if(this.getAvailability() == Availability.IN_USE)
			out += "User : \n" + this.user.toString() + "\n";
		else 
			out += "User : null\n";

		for (Component c : this.ListComponent) {
			out += c.toString();
			out += "\n";
		}
		
		return out;
	}

}
