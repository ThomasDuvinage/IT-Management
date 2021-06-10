package guiController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import Component.ComputerStation;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetailStationController extends Controller implements Initializable{
	@FXML
	private Label l_station;
	@FXML
	private Label l_occupant;
	@FXML
	private Label l_state;
	@FXML
	private Label l_zone;
	@FXML
	private Label l_building;
	@FXML
	private Label l_room;
	@FXML
	private Button b_ok;
	
	private ComputerStation cs;
	
	public DetailStationController(ScreenController screen_controller, ComputerStation cs) {
		super(screen_controller);
		this.cs = cs;
	}

	// Event Listener on Button[#b_ok].onAction
	@FXML
	public void quitButton(ActionEvent event) {
		Stage stage = (Stage) b_ok.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.l_station.setText(this.cs.getName());
		
		if(this.cs.getUser() != null) 
			this.l_occupant.setText(this.cs.getUser().getName());
		else
			this.l_occupant.setText("");
		this.l_state.setText(this.cs.getState().toString());
		this.l_room.setText(this.cs.getParentClassroom().getName());
		this.l_building.setText(this.cs.getParentClassroom().getParentBuilding().getName());
		this.l_zone.setText(this.cs.getParentClassroom().getParentBuilding().getParentZone().getName());
	}
}
