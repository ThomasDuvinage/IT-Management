package guiController;

import java.net.URL;
import java.util.ResourceBundle;

import Area.Building;
import Area.Classroom;
import Area.Zone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.ITManagementDB;

public class AddStationController extends Controller implements Initializable {
	@FXML
	private ComboBox<Zone> ComboZone;
	@FXML
	private ComboBox<Building> ComboBuilding;
	@FXML
	private ComboBox<Classroom> ComboRoom;
	@FXML
	private Button b_add;
	@FXML
	private Button b_cancel;
	
	private ITManagementDB model_it_bdd;
	private String selected_zone;
	private String selected_building;
	private String selected_classroom;

	public AddStationController(ScreenController screen_controller,ITManagementDB it_bdd) {
		super(screen_controller);
		this.model_it_bdd = it_bdd;
		
		this.selected_building = null;
		this.selected_classroom = null;
		this.selected_zone = null;
	}

	// Event Listener on Button[#b_cancel].onAction
	@FXML
	public void quit(ActionEvent event) {
		Stage stage = (Stage) b_cancel.getScene().getWindow();
		stage.close();
	}
	
	// Event Listener on ComboBox[#ComboZone].onAction
	@FXML
	public void setBatimentChoice(ActionEvent event) {
		this.selected_zone = ComboZone.getSelectionModel().getSelectedItem().getName();
		this.selected_building = null;

		ComboBuilding.getItems().setAll(this.model_it_bdd.getBuildingByZone(this.selected_zone));

	}

	// Event Listener on ComboBox[#ComboBuildings].onAction
	@FXML
	public void setClassrooms(ActionEvent event) {
		this.selected_building = ComboBuilding.getSelectionModel().getSelectedItem().getName();
		this.selected_classroom = null;

		ComboRoom.getItems().setAll(this.model_it_bdd.getClassroomsByBuildingName(this.selected_zone, this.selected_building));

	}

	@FXML
	public void getClassroom(ActionEvent event) {
		this.selected_classroom = ComboRoom.getSelectionModel().getSelectedItem().getName();
		
		if(selected_classroom != null) {
			this.b_add.setDisable(false);
		}
	}
	
	// Event Listener on Button[#b_add].onAction
	@FXML
	public void add_component(ActionEvent event) {
		if(selected_classroom != null && selected_building != null && selected_zone != null) {
			this.model_it_bdd.addComputerStation(selected_zone, selected_building, selected_classroom);
			
			this.selected_building = null;
			this.selected_classroom = null;
			this.selected_zone = null;
			this.b_add.setDisable(true);
			
			// Quit page
			Stage stage = (Stage) b_cancel.getScene().getWindow();
			stage.close();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ComboZone.getItems().addAll(this.model_it_bdd.getAllZone());
		this.b_add.setDisable(true);
		
	}
}
