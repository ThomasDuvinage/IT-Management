package guiController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.UserDB;

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
	
   private void showAlertErrorConnexion() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
 
        alert.setHeaderText("Identifiant ou mot de passe erroné.");
 
        alert.showAndWait();
    }


	// Event Listener on Button[#b_login].onAction
	@FXML
	public void login(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
			if (this.model_userDB.checkConnect(f_username.getText().toString(), f_password.getText().toString())) {			
				this.main_controller.setUser(this.model_userDB.getUserByNamePwd(f_username.getText().toString(), f_password.getText().toString()));
				this.screen_controller.activate("main");
				
				f_username.setText("");
				f_password.setText("");
			} else {
				this.showAlertErrorConnexion();
				f_password.setText("");
			}	
		}	
	}

	// Event Listener on Button[#b_quit].onAction
	@FXML
	public void quit(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
			System.exit(0);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
