package guiController;

import Area.Zone;
import User.User;
import Area.Building;
import Area.Classroom;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ComboBox;


public class MainController extends Controller implements Initializable {
	@FXML
	private MenuItem MenuBarProfile;
	@FXML
	private MenuItem MenuBArLogOut;
	@FXML
	private MenuItem MenuBarExit;
	@FXML
	private TextField searchBar;
	@FXML
	private ComboBox<Zone> ComboZone;
	@FXML
	private ComboBox<Building> ComboBuilding;
	@FXML
	private ComboBox<Building> ComboRoom;
	@FXML
	private Button ButtonAddComputerStation;
		
	private User main_user;
	
	
	public MainController(ScreenController screen_controller) {
		super(screen_controller);
	}
	
	public void setUser(User u) {
		this.main_user = u;
		System.out.println(this.main_user.toString());
	}

	// Event Listener on MenuItem[#MenuBarProfile].onAction
	@FXML
	public void seeProfile(ActionEvent event) {
		// TODO Autogenerated
	}
	
	// Event Listener on MenuItem[#MenuBArLogOut].onAction
	@FXML
	public void logOut(ActionEvent event) {
		// TODO Autogenerated
	}
	
	// Event Listener on MenuItem[#MenuBarExit].onAction
	@FXML
	public void exitApp(ActionEvent event) {
		System.exit(0);
	}
	
	// Event Listener on ComboBox[#ComboZone].onAction
	@FXML
	public void setBatimentChoice(ActionEvent event) {
		// TODO Autogenerated
	}
	
	// Event Listener on ComboBox[#ComboBuildings].onAction
	@FXML
	public void setClassrooms(ActionEvent event) {
		// TODO Autogenerated
	}
	
	// Event Listener on Button[#ButtonAddComputerStation].onAction
	@FXML
	public void addComputerStation(ActionEvent event) {
		// TODO Autogenerated
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Zone a = new Zone(1);
		Zone b = new Zone(2);
		
		ComboZone.getItems().addAll(a, b);
	}
}