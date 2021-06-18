package Component;

public class Screen extends Component {
	public Screen(int id) {
		super(id);
		this.type = ComponentType.SCREEN;
		this.setName();
	}
	
	@Override
	public String getImage(int size) {
		return "Screen_" + size + ".png";
	}
}
