package guiController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

import Area.Building;
import Area.Classroom;
import Area.Zone;
import Component.Component;
import Component.ComputerStation;
import Component.State;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ITManagementDB;

public class EditComponentController extends Controller implements Initializable{
	@FXML
	private ImageView l_component_type;
	@FXML
	private Label l_component;
	@FXML
	private ComboBox<State> ComboState;
	@FXML
	private Button b_ok;
	
	private ITManagementDB model_it_bdd;
	private Component cp;
	
	public EditComponentController(ScreenController screen_controller, Component cp, ITManagementDB itBDD) {
		super(screen_controller);
		this.cp = cp;
		this.model_it_bdd = itBDD;
	}

	// Event Listener on Button[#b_ok].onAction
	@FXML
	public void edit(ActionEvent event) {
		if (ComboState.getSelectionModel().getSelectedItem() != this.cp.getState()) {
			this.model_it_bdd.changeComponentState(this.cp, ComboState.getSelectionModel().getSelectedItem());
		}
		
		quit(event);
	}
	
	// Event Listener on Button[#b_cancel].onAction
	@FXML
	public void quit(ActionEvent event) {
		Stage stage = (Stage) b_ok.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.l_component_type.setImage(new Image("file:../../img/" + this.cp.getImage(64)));
		this.l_component.setText(this.cp.getName());
		ComboState.getItems().addAll(State.values());
		ComboState.setValue(this.cp.getState());
	}
}
