package Component;

import java.util.ArrayList;

/**
 * 
 * @brief Enumeration to define the state of the computer station.
 *	The computer station's state is updated depending on its components states.
 *
 */
enum ComputerStationState{
	OUT_OF_SERVICE,
	IN_SERVICE
}

/**
 * TODO 
 * 
 * implement the following method :
 * 	- replaceComponent(c, new_c)
 * 	- deleteComponen(c)
 * ...
 */

/**
 * 
 * @author GertrudeKambouKemamen, AhmetAdam, ThomasDuvinage
 * 
 * @brief This class defined a computer station made of a mouse, a screen, a keyboard, a system unit.
 *
 */

public class ComputerStation {
	private String id;
	private ComputerStationState state;
	
	private ArrayList<Component> ListComponent;
	
	public ComputerStation(String id) {
		this.id = id;
		this.ListComponent = new ArrayList<Component>();
		this.state = ComputerStationState.IN_SERVICE;
	}
	
	public void addComponent(Component c) {
		this.ListComponent.add(c);
	}
	
	
	public void updateState() {
		for(int i = 0; i < ListComponent.size();i++) {
			if(ListComponent.get(i).state == State.HS || ListComponent.get(i).state == State.TO_BE_REPARED) {
				this.state = ComputerStationState.OUT_OF_SERVICE;
			}
		}
	}
	
	public String toString() {
		String out = "";
		out += "Computer Station ID : " + this.id + "\n";
		out += "State : " + this.state.toString() + "\n";
		
		for(Component c: this.ListComponent){
            out += c.toString();
            out += "\n";
        }
		
		return out;
	}
	
}
