package Component;

import java.util.ArrayList;

import Area.Classroom;
import User.User;


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

public class ComputerStation extends Component {
	private ArrayList<Component> ListComponent;
	
	private User user;

	public ComputerStation(int id, Classroom parentClassroom) {
		super(id, parentClassroom);
		this.ListComponent = new ArrayList<Component>();
		setComputerStation();
		
		this.state = State.GOOD;
		this.type = ComponentType.COMPUTER_STATION;
		
		this.setName();
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
	
	public User getUser() {
		return this.user;
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
			Mouse mouse = new Mouse(this.id);
			mouse.setParentComputerStation(this);
			this.ListComponent.add(mouse);
			break;
		case SCREEN:
			Screen screen = new Screen(this.id);
			screen.setParentComputerStation(this);
			this.ListComponent.add(screen);
			break;
		case KEYBOARD:
			Keyboard keyboard = new Keyboard(this.id);
			keyboard.setParentComputerStation(this);
			this.ListComponent.add(keyboard);
			break;
		case SYSTEM_UNIT:
			SystemUnit system_unit = new SystemUnit(this.id);
			system_unit.setParentComputerStation(this);
			this.ListComponent.add(system_unit);
			break;
		}
	}
	
	public ArrayList<Component> getComponents(){
		return this.ListComponent;
	}

	public void addComponent(Component c) {
		this.ListComponent.add(c);
	}
	
	public void deleteComponent(Component c) {
		this.ListComponent.remove(c);
	}

	public int getId() {
		return this.id;
	}

	public void updateState() {
		State worst = State.GOOD;
			
		int i = ListComponent.size() - 1;
		while (i >= 0 && worst != State.HS) {
			if (ListComponent.get(i).getState() == State.HS) {
				worst = State.HS;
			}
			else {
				if (ListComponent.get(i).getState() == State.TO_BE_REPARED) {
					worst = State.TO_BE_REPARED;
				}
			}
			i--;
		}
		
		this.state = worst;
	}

	/*public String toString() {
		String out = "";
		out += "Computer Station ID : " + this.id + "\n";
		out += "Parent Classroom ID : " + this.getParentClassroom().getId() + "\n";
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
	}*/
	
	@Override
	public String getImage(int size) {
		return "Station_" + size + ".png";
	}
	
	public String toString() {
		return this.getName();
	}
}
