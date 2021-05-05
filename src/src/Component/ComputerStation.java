package Component;

import java.util.ArrayList;

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
	private String id;
	private ComputerStationState state;
	private ArrayList<Component> ListComponent;
	
	protected Availability access;

	public ComputerStation(int id) {
		this.id = Integer.toString(id);
		this.ListComponent = new ArrayList<Component>();
		setComputerStation();
		
		this.state = ComputerStationState.IN_SERVICE;
		this.access = Availability.FREE; //TODO set 
	}
	
	private void setComputerStation() {
		this.addNewComponent(ComponentType.MOUSE);
		this.addNewComponent(ComponentType.SCREEN);
		this.addNewComponent(ComponentType.KEYBOARD);
		this.addNewComponent(ComponentType.SYSTEM_UNIT);
	}
	
	public void setAvailability(Availability a) {
		this.access = a;
	}
	
	public Availability getAvailability() {
		return this.access;
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

	public String getId() {
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
		out += "State : " + this.state.toString() + "\n";
		out += "Availability : " + this.access.toString() + "\n";

		for (Component c : this.ListComponent) {
			out += c.toString();
			out += "\n";
		}
		
		return out;
	}

}
