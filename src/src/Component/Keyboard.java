package Component;

public class Keyboard extends Component {
	public Keyboard(int id) {
		super(id);
		this.type = ComponentType.KEYBOARD;
		this.setName();
	}

	@Override
	public String getImage(int size) {
		return "Keyboard_" + size + ".png";
	}
}
