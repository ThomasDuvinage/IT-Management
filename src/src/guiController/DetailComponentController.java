package guiController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import Component.Component;
import Component.ComputerStation;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DetailComponentController extends Controller implements Initializable{
	@FXML
	private ImageView l_component_type;
	@FXML
	private Label l_component;
	@FXML
	private Label l_occupant;
	@FXML
	private Label l_state;
	@FXML
	private Label l_station;
	@FXML
	private Button b_ok;
	
	private Component cp;
	
	public DetailComponentController(ScreenController screen_controller, Component cp) {
		super(screen_controller);
		this.cp = cp;
	}

	// Event Listener on Button[#b_ok].onAction
	@FXML
	public void quitButton(ActionEvent event) {
		Stage stage = (Stage) b_ok.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.l_component_type.setImage(new Image("file:../../img/" + this.cp.getImage(64)));
		this.l_component.setText(this.cp.getName());
		
		if (this.cp.getParentComputerStation().getUser() != null) 
			this.l_occupant.setText(this.cp.getParentComputerStation().getUser().getName());
		else
			this.l_occupant.setText("");
		this.l_state.setText(this.cp.getState().toString());
		this.l_station.setText(this.cp.getParentComputerStation().getName());
	}
}
