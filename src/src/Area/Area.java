package Area;

enum ID {
	CLASS,
	BAT,
	ZONE,
}

public class Area {
	protected int id;
	protected String name;
	
	protected ID type;
	
	public Area(String name) {
		this.name = name;
	}
	
	public Area(int area_id) {
		this.id = area_id;
	}
	
	public Area(int area_id, ID type, String name) {
		this.id = area_id;
		this.type = type;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public ID getType() {
		return this.type;
	}
	
	public String getInfo() {
		String out = "";
		out += "Area ID : " + this.id + "\n";
		out += "Type : " + this.type.toString() + "\n";
		
		return out;
	}
	
	public String toString() {
		String out = name + "\n";
		
		return out;
	}
}
