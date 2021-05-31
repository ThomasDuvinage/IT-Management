package Area;

enum ID {
	CLASS,
	BAT,
	ZONE,
}

public class Area {
	protected int id;
	protected ID type;
	
	public Area(int area_id) {
		this.id = area_id;
	}
	
	public Area(int area_id, ID type) {
		this.id = area_id;
		this.type = type;
	}
	
	public int getId() {
		return this.id;
	}
	
	public ID getType() {
		return this.type;
	}
	
	public String toString() {
		String out = "";
		out += "Area ID : " + this.id + "\n";
		out += "Type : " + this.type.toString() + "\n";
		
		return out;
	}
}
