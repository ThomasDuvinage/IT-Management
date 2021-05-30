package guiController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ConnexionController {
	
	@FXML
	public TextField f_username;
	
	@FXML
	public PasswordField f_password;
	
	public void login() {
		// TODO: check in the database that username + password match
		if (f_username.getText().toString().equals("admin")) {
			if (f_password.getText().toString().equals("my_password")) {
				System.out.println("Credentials correct");
			}
			else {
				System.out.println("Invalid username or password");
			}
		}
		else {
			System.out.println("Invalid username");
		}
	}
	
	public void quit() {
		System.exit(0);
	}
}
