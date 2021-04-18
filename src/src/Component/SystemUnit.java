package Component;

public class SystemUnit extends Component {
	private String ip_adress;
	
	public SystemUnit() {
		this.type = ComponentType.SYSTEM_UNIT;
		this.ip_adress = "192.168.1.0"; // TODO to be change or define by argument in constructor
	}
	
	public String toString() {
		String out = "";
		out += "\tComponent ID : " + this.id + "\n";
		out += "\t\tType : " + this.type.toString() + "\n";
		out += "\t\tState : " + this.state.toString() + "\n";
		out += "\t\tAvailability : " + this.access.toString() + "\n";
		out += "\t\tIP adress : " + this.ip_adress + "\n";
		
		return out;
	}
}
