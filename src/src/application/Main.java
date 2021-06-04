package application;

import guiController.ConnexionController;
import guiController.MainController;
import guiController.ScreenController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.UserDB;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private ConnexionController connexion_controller;
	private MainController main_controller;
	
	private UserDB model_users;
	
	private ScreenController screenController;

	@Override
	public void start(Stage primaryStage) {
		try {			
			screenController = new ScreenController(primaryStage);
			
			initializeModels();
			initializeControllers();
			setViews();
			screenController.activate("connexion");
			
			primaryStage.setTitle("Menu principal");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setViews() {
		FXMLLoader connexion_file = new FXMLLoader(getClass().getResource("connexion.fxml"));
		connexion_file.setController(connexion_controller);
		screenController.addScreen("connexion", connexion_file);
		
		FXMLLoader main_view = new FXMLLoader(getClass().getResource("main.fxml"));
		main_view.setController(main_controller);
		screenController.addScreen("main", main_view);
	}
	
	private void initializeModels() {
		this.model_users = new UserDB();
	}

	private void initializeControllers() {
		this.main_controller = new MainController(screenController);
		
		this.connexion_controller = new ConnexionController(this.model_users, screenController);
		this.connexion_controller.setNextPageController(main_controller);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
