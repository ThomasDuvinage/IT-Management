package guiController;

import java.util.HashMap;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenController {
	private HashMap<String, Scene> screenMap = new HashMap<>();
	private Stage main;

	public ScreenController(Stage main) {
		this.main = main;
	}

	public void addScreen(String name, Scene scene) {
		screenMap.put(name, scene);
	}

	public void removeScreen(String name) {
		screenMap.remove(name);
	}

	public void activate(String name) {		
		screenMap.get(name).getStylesheets().add("application.css");
		main.setTitle(name);
		main.setScene(screenMap.get(name));
	}
}
