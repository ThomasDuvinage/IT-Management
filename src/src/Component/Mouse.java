package Component;

public class Mouse extends Component {
	public Mouse(int id) {
		super(id);
		this.type = ComponentType.MOUSE;
		this.setName();
	}
	
	@Override
	public String getImage(int size) {
		return "Mouse_" + size + ".png";
	}
}
