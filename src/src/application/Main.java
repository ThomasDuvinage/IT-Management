package application;
	
import guiController.ScreenController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {			
			ScreenController screenController = new ScreenController(primaryStage);
			
			screenController.addScreen("connexion", new Scene(FXMLLoader.load(getClass().getResource("connexion.fxml"))));
			screenController.addScreen("main", new Scene(FXMLLoader.load(getClass().getResource("main.fxml"))));
			
			screenController.activate("main");
						
			primaryStage.setTitle("Authentification");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
