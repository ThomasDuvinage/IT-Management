package guiController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ConnexionController implements Initializable{
	@FXML
	private TextField f_username;
	@FXML
	private PasswordField f_password;


	// Event Listener on Button[#b_login].onAction
	@FXML
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
