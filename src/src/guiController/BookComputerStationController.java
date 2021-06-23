package guiController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Component.ComputerStation;
import User.User;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Booking;
import model.ITManagementDB;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

public class BookComputerStationController extends Controller implements Initializable {
	@FXML
	private Label l_station;
	@FXML
	private DatePicker dp_occpuy_date;
	@FXML
	private ComboBox<Integer> cb_occupy_h_s;
	@FXML
	private ComboBox<Integer> cb_occupy_m_s;
	@FXML
	private ComboBox<Integer> cb_occupy_h_e;
	@FXML
	private ComboBox<Integer> cb_occupy_m_e;
	@FXML
	private Button b_ok;
	@FXML
	private Button b_cancel;
	
	private ITManagementDB model_it_bdd;
	private User user;
	private ComputerStation station;
	
	public BookComputerStationController(ScreenController screen_controller, ITManagementDB itBDD, User u, ComputerStation cs) {
		super(screen_controller);
		this.model_it_bdd = itBDD;
		this.user = u;
		this.station = cs;
	}
	
	private void showAlertErrorBooking(String err) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de r�servation");
 
        alert.setHeaderText(err);
 
        alert.showAndWait();
    }

	// Event Listener on Button[#b_ok].onAction
	@FXML
	public void book(ActionEvent event) {
		if (dp_occpuy_date.getValue() == null || cb_occupy_h_s.getValue() == null || cb_occupy_h_e.getValue() == null || cb_occupy_m_s.getValue() == null || cb_occupy_m_e.getValue() == null) {
			showAlertErrorBooking("Champs incomplets.");
		}
		else {
			if (cb_occupy_h_s.getValue() > cb_occupy_h_e.getValue()) {
				showAlertErrorBooking("P�riode invalide.");
			}
			else {
				if (cb_occupy_h_s.getValue() == cb_occupy_h_e.getValue() && cb_occupy_m_s.getValue() >= cb_occupy_m_e.getValue()) {
					showAlertErrorBooking("P�riode invalide.");
				}
				else {
					if (model_it_bdd.isComputerStationOccupied(this.station, dp_occpuy_date.getValue(), cb_occupy_h_s.getValue(), cb_occupy_m_s.getValue(), cb_occupy_h_e.getValue(), cb_occupy_m_e.getValue())) {
						showAlertErrorBooking("Le poste est occup� sur ce cr�neau.");
					}
					else {
						boolean exists = false;
						
						int i = this.model_it_bdd.getUserBookings(this.user).size() - 1;
						while (i >= 0 && !exists) {
							Booking booking = this.model_it_bdd.getUserBookings(this.user).get(i);
							if (booking.isSameBooking(dp_occpuy_date.getValue(), cb_occupy_h_s.getValue(), cb_occupy_m_s.getValue(), cb_occupy_h_e.getValue(), cb_occupy_m_e.getValue())) {
								booking.addComputerStation(station);
								exists = true;
							}
							i--;
						}
						
						if (!exists) {
							model_it_bdd.addBooking(
								new Booking(
									user,
									station,
									dp_occpuy_date.getValue(),
									cb_occupy_h_s.getValue(),
									cb_occupy_m_s.getValue(),
									cb_occupy_h_e.getValue(),
									cb_occupy_m_e.getValue()
								)
							);
						}
						
						quit(event);
					}
				}
			}
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
		// TODO Auto-generated method stub
		dp_occpuy_date.setDayCellFactory(param -> new DateCell() {
	        @Override
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            setDisable(empty || date.compareTo(LocalDate.now()) < 0 );
	        }
	    });
		cb_occupy_h_s.getItems().setAll(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
		cb_occupy_m_s.getItems().setAll(0, 15, 30, 45);
		cb_occupy_h_e.getItems().setAll(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
		cb_occupy_m_e.getItems().setAll(0, 15, 30, 45);
	}
}
