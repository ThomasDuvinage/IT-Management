package guiController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import User.Departement;
import User.User;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

public class EditTeacherController extends Controller implements Initializable {
	@FXML
	private TextField f_last_name;
	@FXML
	private TextField f_first_name;
	@FXML
	private TextField f_username;
	@FXML
	private ComboBox<Departement> cb_department;
	@FXML
	private Button b_ok;
	@FXML
	private Button b_cancel;
	
	private User user;
	
	public EditTeacherController(ScreenController screen_controller, User u) {
		super(screen_controller);
		this.user = u;
	}

	// Event Listener on Button[#b_ok].onAction
	@FXML
	public void edit(ActionEvent event) {
		boolean success = false;
		if(!this.f_last_name.getText().isBlank()) {
			if(!this.f_first_name.getText().isBlank()) {
				if(!this.f_username.getText().isBlank()) {
					if(this.cb_department.getSelectionModel().getSelectedItem() != null) {
						this.user.setDepartement(this.cb_department.getSelectionModel().getSelectedItem());
						this.user.setPseudo(this.f_username.getText());
						this.user.setFirstName(this.f_first_name.getText());
						this.user.setName(this.f_last_name.getText());
						success = true;
					}
				}
			}
		}
		
		if(!success) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur modification enseignant");

			alert.setHeaderText("Echec de modification");

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
