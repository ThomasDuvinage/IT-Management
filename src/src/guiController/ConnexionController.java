package guiController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UserDB;

public class ConnexionController implements Initializable {
	@FXML
	private TextField f_username;
	@FXML
	private PasswordField f_password;

	private UserDB model_userDB;
	
	private ScreenController screen_controller;

	public ConnexionController(UserDB userDB, ScreenController screen_controller) {
		this.model_userDB = userDB;
		this.screen_controller = screen_controller;
	}

	// Event Listener on Button[#b_login].onAction
	@FXML
	public void login() {
		// TODO: check in the database that username + password match
		if (this.model_userDB.checkConnect(f_username.getText().toString(), f_password.getText().toString())) {
			System.out.println("Credentials correct");
			this.screen_controller.activate("main");
		} else {
			System.out.println("Invalid username or password");
		}
	}

	// Event Listener on Button[#b_quit].onAction
	@FXML
	public void quit() {
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
