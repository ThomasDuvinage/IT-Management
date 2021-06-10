package Component;

public class Mouse extends Component {
	public Mouse(int id) {
		super(id);
		this.type = ComponentType.MOUSE;
		this.setName();
	}

}
