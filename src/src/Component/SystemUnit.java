package Component;

public class SystemUnit extends Component {
	private String ip_adress;
	
	public SystemUnit(int id) {
		super(id);
		this.type = ComponentType.SYSTEM_UNIT;
		this.setName();
		
		this.ip_adress = "192.168.1.0"; // TODO to be change or define by argument in constructor
	}
	
	public String toString() {
		String out = "";
		out += "\tComponent ID : " + this.getId() + "\n";
		out += "\t\tType : " + this.getType().toString() + "\n";
		out += "\t\tState : " + this.getState().toString() + "\n";
		out += "\t\tIP adress : " + this.ip_adress + "\n";
		
		return out;
	}
}
