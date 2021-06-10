package guiController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.UserDB;

import java.net.URL;
import java.util.ResourceBundle;

import User.Departement;
import User.Teacher;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.PasswordField;

public class AddTeacherController extends Controller implements Initializable {
	@FXML
	private TextField f_last_name;
	@FXML
	private TextField f_first_name;
	@FXML
	private TextField f_username;
	@FXML
	private PasswordField f_password;
	@FXML
	private PasswordField f_password_confirm;
	@FXML
	private ComboBox<Departement> cb_department;
	@FXML
	private Button b_add;
	@FXML
	private Button b_cancel;
	
	private UserDB model_user_bdd; 
	
	public AddTeacherController(ScreenController screen_controller, UserDB user_bdd) {
		super(screen_controller);
		this.model_user_bdd = user_bdd;
	}

	// Event Listener on Button[#b_add].onAction
	@FXML
	public void add(ActionEvent event) {
		boolean success = false;
		if(!this.f_first_name.getText().isBlank()) {
			if(!this.f_last_name.getText().isBlank()) {
				if(!this.f_username.getText().isBlank()) {
					if(!this.f_password.getText().isBlank() && this.f_password.getText().equals(this.f_password_confirm.getText())) {
						if(this.cb_department.getSelectionModel().getSelectedItem() != null) {
							this.model_user_bdd.addUser(new Teacher(this.f_last_name.getText(), this.f_first_name.getText(),this.f_username.getText(), this.f_password.getText(), this.cb_department.getSelectionModel().getSelectedItem()));
							success = true;
						}
					}
				}
			}
		}
		
		if(!success) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur ajout enseignant");

			alert.setHeaderText("Echec d'ajout");

			alert.showAndWait();
		}
		else {
			Stage stage = (Stage) b_cancel.getScene().getWindow();
			stage.close();
		}

	}
	
	// Event Listener on Button[#b_cancel].onAction
	@FXML
	public void quit(ActionEvent event) {
		Stage stage = (Stage) b_cancel.getScene().getWindow();
		stage.close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.cb_department.getItems().addAll(Departement.values());
		
	}
}
