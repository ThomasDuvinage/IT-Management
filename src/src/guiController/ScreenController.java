package guiController;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenController {
	private HashMap<String, Scene> screenMap;
	private Stage main;

	public ScreenController(Stage main) {
		this.screenMap = new HashMap<>();

		this.main = main;
	
	}

	public void addScreen(String name, FXMLLoader fxml_load) {
		try {
			screenMap.put(name, new Scene(fxml_load.load()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void removeScreen(String name) {
		screenMap.remove(name);
	}

	public void activate(String name) {		
		//screenMap.get(name).getStylesheets().add(this.getClass().getResource("/application/application.css").toExternalForm());
		main.setTitle(name);
		main.setScene(screenMap.get(name));
	}
}
