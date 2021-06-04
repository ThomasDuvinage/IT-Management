package guiController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UserDB;
import guiAlert.ConnexionAlert;

public class ConnexionController extends Controller implements Initializable {
	@FXML
	private TextField f_username;
	@FXML
	private PasswordField f_password;

	private UserDB model_userDB;
	
	private MainController main_controller; // TODO discuss if it is the best solution
	

	public ConnexionController(UserDB userDB, ScreenController screen_controller) {
		super(screen_controller);
		
		this.model_userDB = userDB;
	}
	
	public void setNextPageController(MainController mc) {
		this.main_controller = mc;
	}
	
	

	// Event Listener on Button[#b_login].onAction
	@FXML
	public void login() {
		if (this.model_userDB.checkConnect(f_username.getText().toString(), f_password.getText().toString())) {
			System.out.println("Credentials correct");
			
			this.main_controller.setUser(this.model_userDB.getUserByNamePwd(f_username.getText().toString(), f_password.getText().toString()));
			this.screen_controller.activate("main");
			
			f_username.setText(null);
			f_password.setText(null);
		} else {
			new ConnexionAlert();
			f_password.setText(null);
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
