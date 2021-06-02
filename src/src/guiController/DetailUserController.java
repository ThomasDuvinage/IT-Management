package guiController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DetailUserController extends Controller implements Initializable {
	@FXML
	private ImageView i_user;
	@FXML
	private Label l_last_name;
	@FXML
	private Label l_first_name;
	@FXML
	private Label l_username;
	@FXML
	private Label l_department;
	@FXML
	private Button b_change_password;
	@FXML
	private Button b_close;
	
	private User main_user;

	public DetailUserController(ScreenController screen_controller, User user) {
		super(screen_controller);
		this.main_user = user;
	}

	// Event Listener on Button[#b_change_password].onAction
	@FXML
	public void changePassword(ActionEvent event) throws IOException {
		FXMLLoader change_password_loader = new FXMLLoader();
		change_password_loader.setLocation(getClass().getResource("/application/change_password.fxml"));
		change_password_loader.setController(new ChangePasswordController(this.screen_controller, this.main_user));
		
		Scene change_password_scene = new Scene(change_password_loader.load());
		
		Stage stage = (Stage) b_change_password.getScene().getWindow();
		stage.setScene(change_password_scene);
	}
	
	// Event Listener on Button[#b_close].onAction
	@FXML
	public void quit(ActionEvent event) {
		Stage stage = (Stage) b_close.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File profile_image_file;
		Image profile_image;
		
		if(main_user.isAdmin()) 
			profile_image_file = new File("img/administrator_64.png");
		else
			profile_image_file = new File("img/teacher_64.png");
		
		profile_image = new Image(profile_image_file.toURI().toString());
		i_user.setImage(profile_image);
		
		this.l_last_name.setText(main_user.getName());
		this.l_first_name.setText(main_user.getFirstName());
		this.l_username.setText(main_user.getPseudo());
		this.l_department.setText(main_user.getDepartement().toString());
	}

}
