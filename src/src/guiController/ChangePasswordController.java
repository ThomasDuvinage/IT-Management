package guiController;

import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePasswordController extends Controller {
	@FXML
	private TextField f_old_password;
	@FXML
	private PasswordField f_new_password;
	@FXML
	private PasswordField f_password_confirm;
	@FXML
	private Button b_ok;
	@FXML
	private Button b_cancel;
	
	private User main_user;

	public ChangePasswordController(ScreenController screen_controller, User user) {
		super(screen_controller);
		this.main_user = user;
	}
	
	// Event Listener on Button[#b_ok].onAction
	@FXML
	public void validate(ActionEvent event) {
		if(f_old_password.getText().equals(this.main_user.getPWD())) {
			if(f_new_password.getText().equals(f_password_confirm.getText()) && !f_new_password.getText().isBlank()) {
				this.main_user.setPWD(f_new_password.getText());
				
				// Delete text in text fields
				f_old_password.setText(null);
				f_new_password.setText(null);
				f_password_confirm.setText(null);
			}
			else {
				//TODO change error text
				System.out.println("ChangePassWordController : Erreur de nouveau mot de passe");
			}
		}
		else {
			//TODO change error text
			System.out.println("ChangePassWordController : Erreur de mot de passe actuel");
		}
	}
	// Event Listener on Button[#b_cancel].onAction
	@FXML
	public void quit(ActionEvent event) {
		Stage stage = (Stage) this.b_cancel.getScene().getWindow();
		stage.close();
		
		this.screen_controller.activate("main");
	}

}
