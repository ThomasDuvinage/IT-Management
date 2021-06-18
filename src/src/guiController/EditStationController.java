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
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ITManagementDB;

public class EditStationController extends Controller implements Initializable{
	@FXML
	private ImageView l_component_type;
	@FXML
	private Label l_station;
	@FXML
	private ComboBox<Zone> ComboZone;
	@FXML
	private ComboBox<Building> ComboBuilding;
	@FXML
	private ComboBox<Classroom> ComboRoom;
	@FXML
	private Button b_ok;
	
	private ITManagementDB model_it_bdd;
	private ComputerStation cs;
	
	private String selected_zone;
	private String selected_building;
	private String selected_classroom;
	
	public EditStationController(ScreenController screen_controller, ComputerStation cs, ITManagementDB itBDD) {
		super(screen_controller);
		this.cs = cs;
		this.model_it_bdd = itBDD;
	}
	
	// Event Listener on ComboBox[#ComboZone].onAction
	@FXML
	public void setBatimentChoice(ActionEvent event) {
		if (ComboZone.getSelectionModel().getSelectedItem().getName() != this.selected_zone) {
			this.selected_zone = ComboZone.getSelectionModel().getSelectedItem().getName();
			this.selected_building = null;
			this.selected_classroom = null;
			
			//ComboBuilding.getSelectionModel().clearSelection();
			ComboRoom.getSelectionModel().clearSelection();
			
			ComboBuilding.getItems().setAll(this.model_it_bdd.getBuildingByZone(this.selected_zone));
			ComboBuilding.setDisable(false);
			ComboRoom.setDisable(true);
			b_ok.setDisable(true);
		}
	}
		
	// Event Listener on ComboBox[#ComboBuildings].onAction
	@FXML
	public void setClassrooms(ActionEvent event) {
		if (ComboBuilding.getSelectionModel().getSelectedItem().getName() != this.selected_building) {
			this.selected_building = ComboBuilding.getSelectionModel().getSelectedItem().getName();
			this.selected_classroom = null;
			
			//ComboRoom.getSelectionModel().clearSelection();
			ComboRoom.getItems().setAll(this.model_it_bdd.getClassroomsByBuildingName(this.selected_zone, this.selected_building));
			ComboRoom.setDisable(false);
			b_ok.setDisable(true);
		}
	}
	
	// Event Listener on ComboBox[#ComboRoom].onAction
	@FXML
	public void setStation(ActionEvent event) {
		if (ComboRoom.getSelectionModel().getSelectedItem().getName() != this.selected_classroom) {
			this.selected_classroom = ComboRoom.getSelectionModel().getSelectedItem().getName();
			b_ok.setDisable(false);
		}
	}

	// Event Listener on Button[#b_ok].onAction
	@FXML
	public void edit(ActionEvent event) {
		if (ComboRoom.getSelectionModel().getSelectedItem() != this.cs.getParentClassroom()) {
			this.model_it_bdd.moveComputerStationTo(
				selected_zone,
				selected_building,
				selected_classroom,
				cs
			);
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
		this.l_station.setText(this.cs.getName());
		ComboZone.getItems().addAll(this.model_it_bdd.getAllZone());
	}
}
