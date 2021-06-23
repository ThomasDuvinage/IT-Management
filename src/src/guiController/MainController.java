package guiController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Area.Area;
import Area.Building;
import Area.Classroom;
import Area.Zone;
import Component.Availability;
import Component.Component;
import Component.ComponentType;
import Component.ComputerStation;
import Component.State;
import User.Departement;
import User.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Booking;
import model.ITManagementDB;
import model.UserDB;

public class MainController extends Controller implements Initializable {
	@FXML
	private MenuItem MenuBarProfile;
	@FXML
	private MenuItem MenuBarLogOut;
	@FXML
	private MenuItem MenuBarExit;
	@FXML
	private TextField searchBarComponent;
	@FXML
	private ComboBox<Zone> ComboZone;
	@FXML
	private ComboBox<Building> ComboBuilding;
	@FXML
	private ComboBox<Classroom> ComboRoom;
	@FXML
	private Button ButtonAddComputerStation;
	@FXML
	private Button b_reserve;
	@FXML
	private TabPane tab_pane;
	@FXML
	private Tab materialTab;
	@FXML
	private Tab reservationTab;
	@FXML
	private Tab teacherTab;
	@FXML
	private TreeTableView TreeTableMaterial;
	@FXML
	private TreeTableColumn componentNameColumn;
	@FXML
	private TreeTableColumn componentAccesibilityColumn;
	@FXML
	private TreeTableColumn componentStateColumn;
	@FXML
	private TextField searchBarReservation;
	@FXML
	private TreeView TreeReservation;
	@FXML
	private TextField searchBarTeacher;
	@FXML
	private ComboBox<Departement> ComboZoneUserDepartement;
	@FXML
	private TreeTableView TreeTableTeachers;
	@FXML
	private TreeTableColumn teacherFirstNameColumn;
	@FXML
	private TreeTableColumn teacherNameColumn;
	@FXML
	private TreeTableColumn teacherDepartementColumn;
	@FXML
	private Button ButtonAddComputerStation1;

	private User main_user;

	private ITManagementDB model_it_bdd;
	private UserDB model_user_bdd;

	private ContextMenu contextMenu;
	private ContextMenu componentContextMenu;
	private ContextMenu bookingContextMenu;
	private ContextMenu bookingStationContextMenu;
	private ContextMenu teacherContextMenu;

	private String selected_zone;
	private String selected_building;
	private String selected_classroom;
	private ComputerStation selected_cs;
	private Component selected_component;
	private Booking selected_booking;
	private ComputerStation selected_station_booking;
	private User selected_user;

	public MainController(ScreenController screen_controller, ITManagementDB itBDD, UserDB userBDD) {
		super(screen_controller);
		this.model_it_bdd = itBDD;
		this.model_user_bdd = userBDD;

		this.selected_building = null;
		this.selected_classroom = null;
		this.selected_zone = null;
	}

	public void setUser(User u) {
		this.main_user = u;
		this.initializeBookingTable();
		System.out.println(this.main_user.toString());
		

		// Hide / Show teacher tab depending on user rights
		if (main_user.isTeacher()) {
			if (tab_pane.getTabs().contains(teacherTab)) {
				tab_pane.getTabs().remove(teacherTab);
			}
			this.ButtonAddComputerStation.setVisible(false);
		}
		else {
			if (main_user.isAdmin()) {
				if (!tab_pane.getTabs().contains(teacherTab)) {
					tab_pane.getTabs().add(teacherTab);
				}
				this.ButtonAddComputerStation.setVisible(true);
			}
		}
	}

	// Event Listener on MenuItem[#MenuBarProfile].onAction
	@FXML
	public void seeProfile(ActionEvent event) throws IOException {

		FXMLLoader detail_user_loader = new FXMLLoader();
		detail_user_loader.setLocation(getClass().getResource("/application/detail_user.fxml"));
		detail_user_loader.setController(new DetailUserController(this.screen_controller, main_user));

		Scene detail_user_scene = new Scene(detail_user_loader.load());
		Stage stage = new Stage();
		stage.setTitle("Profil");
		stage.setScene(detail_user_scene);
		stage.show();
	}

	// Event Listener on MenuItem[#MenuBArLogOut].onAction
	@FXML
	public void logOut(ActionEvent event) {
		this.screen_controller.activate("connexion");
		this.main_user = null;
	}

	// Event Listener on MenuItem[#MenuBarExit].onAction
	@FXML
	public void exitApp(ActionEvent event) {
		System.exit(0);
	}

	// Event Listener on ComboBox[#ComboZone].onAction
	@FXML
	public void setBatimentChoice(ActionEvent event) {
		if (ComboZone.getSelectionModel().getSelectedItem() == null) {
			if (this.selected_zone != null) {
				this.selected_zone = null;
				this.selected_building = null;
				this.selected_classroom = null;
				
				ComboBuilding.getSelectionModel().clearSelection();
				ComboRoom.getSelectionModel().clearSelection();
				
				ComboBuilding.setDisable(true);
				ComboRoom.setDisable(true);
			}
			this.updateTreeTable();
		}
		else {
			if (ComboZone.getSelectionModel().getSelectedItem().getName() != this.selected_zone) {
				this.selected_zone = ComboZone.getSelectionModel().getSelectedItem().getName();
				this.selected_building = null;
				this.selected_classroom = null;
				
				//ComboBuilding.getSelectionModel().clearSelection();
				ComboRoom.getSelectionModel().clearSelection();
				
				ComboBuilding.getItems().setAll(this.model_it_bdd.getBuildingByZone(this.selected_zone));
				ComboBuilding.getItems().add(null);
				ComboBuilding.setDisable(false);
				ComboRoom.setDisable(true);
		
				this.updateTreeTable();
			}
		}
	}

	// Event Listener on ComboBox[#ComboBuildings].onAction
	@FXML
	public void setClassrooms(ActionEvent event) {
		if (ComboBuilding.getSelectionModel().getSelectedItem() == null) {
			if (this.selected_building != null) {
				this.selected_building = null;
				this.selected_classroom = null;
				
				ComboRoom.getSelectionModel().clearSelection();
				ComboRoom.setDisable(true);
			}
			this.updateTreeTable();
		}
		else {
			if (ComboBuilding.getSelectionModel().getSelectedItem().getName() != this.selected_building) {
				this.selected_building = ComboBuilding.getSelectionModel().getSelectedItem().getName();
				this.selected_classroom = null;
				
				//ComboRoom.getSelectionModel().clearSelection();
				ComboRoom.getItems().setAll(this.model_it_bdd.getClassroomsByBuildingName(this.selected_zone, this.selected_building));
				ComboRoom.getItems().add(null);
				ComboRoom.setDisable(false);
		
				this.updateTreeTable();
			}
		}
	}

	@FXML
	public void getComputerStationsClassroom(ActionEvent event) {
		if (ComboRoom.getSelectionModel().getSelectedItem() == null) {
			if (this.selected_classroom != null) {
				this.selected_classroom = null;
				
				this.updateTreeTable();
			}
		}
		else {
			if (ComboRoom.getSelectionModel().getSelectedItem().getName() != this.selected_classroom) {
				this.selected_classroom = ComboRoom.getSelectionModel().getSelectedItem().getName();
				
				this.updateTreeTable();
			}
		}
	}

	// Event Listener on Button[#ButtonAddComputerStation].onAction
	@FXML
	public void addComputerStation(ActionEvent event) throws IOException {
		FXMLLoader add_station_loader = new FXMLLoader();
		add_station_loader.setLocation(getClass().getResource("/application/add_station.fxml"));
		add_station_loader.setController(new AddStationController(this.screen_controller, this, this.model_it_bdd));

		Scene add_station_scene = new Scene(add_station_loader.load());
		Stage stage = new Stage();
		stage.setTitle("Ajouter une station");
		stage.setScene(add_station_scene);
		stage.show();

		this.updateTreeTable();
	}

	@FXML
	public void setUserPerDepartement(ActionEvent event) {
		this.updateTeacherTreeTable(this.model_user_bdd
				.getUserByDepartement(this.ComboZoneUserDepartement.getSelectionModel().getSelectedItem()));
	}

	@FXML
	public void addTeacher(ActionEvent event) throws IOException {
		FXMLLoader add_teacher_loader = new FXMLLoader();
		add_teacher_loader.setLocation(getClass().getResource("/application/add_teacher.fxml"));
		add_teacher_loader.setController(new AddTeacherController(this.screen_controller, this.model_user_bdd));

		Scene add_teacher_scene = new Scene(add_teacher_loader.load());
		Stage stage = new Stage();
		stage.setTitle("Ajouter un enseignant");
		stage.setScene(add_teacher_scene);
		stage.showAndWait();

		this.updateTeacherTreeTable(this.model_user_bdd.getUserDB());
	}

	@FXML
	public void computerStationBook(ActionEvent event) throws IOException {
		FXMLLoader book_station_loader = new FXMLLoader();
		book_station_loader.setLocation(getClass().getResource("/application/occupy_station.fxml"));
		book_station_loader.setController(new BookComputerStationController(this.screen_controller, this.model_it_bdd, this.main_user, this.selected_cs));

		Scene add_station_scene = new Scene(book_station_loader.load());
		Stage stage = new Stage();
		stage.setTitle("Ajouter une station");
		stage.setScene(add_station_scene);
		stage.showAndWait();

		this.updateBookingTable();
	}

	private void initializeContextMenu() {
		this.contextMenu = new ContextMenu();

		Menu add_component = new Menu("Ajouter un mat�riel");

		MenuItem central_unit = new MenuItem("Unit� centrale");
		central_unit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Ajouter une unit� centrale");
				model_it_bdd.addComponentToComputerStation(
					ComponentType.SYSTEM_UNIT,
					selected_cs.getParentClassroom().getParentBuilding().getParentZone().getName(),
					selected_cs.getParentClassroom().getParentBuilding().getName(),
					selected_cs.getParentClassroom().getName(),
					selected_cs.getId()
				);
				updateTreeTable();
			}
		});

		MenuItem screen = new MenuItem("Ecran");
		screen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Ajouter un �cran");
				model_it_bdd.addComponentToComputerStation(
					ComponentType.SCREEN,
					selected_cs.getParentClassroom().getParentBuilding().getParentZone().getName(),
					selected_cs.getParentClassroom().getParentBuilding().getName(),
					selected_cs.getParentClassroom().getName(),
					selected_cs.getId()
				);
				updateTreeTable();
			}
		});

		MenuItem mouse = new MenuItem("Souris");
		mouse.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Ajouter une souris");
				model_it_bdd.addComponentToComputerStation(
					ComponentType.MOUSE,
					selected_cs.getParentClassroom().getParentBuilding().getParentZone().getName(),
					selected_cs.getParentClassroom().getParentBuilding().getName(),
					selected_cs.getParentClassroom().getName(),
					selected_cs.getId()
				);
				updateTreeTable();
			}
		});

		MenuItem keyboard = new MenuItem("Clavier");
		keyboard.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Ajouter un clavier");
				model_it_bdd.addComponentToComputerStation(
					ComponentType.KEYBOARD,
					selected_cs.getParentClassroom().getParentBuilding().getParentZone().getName(),
					selected_cs.getParentClassroom().getParentBuilding().getName(),
					selected_cs.getParentClassroom().getName(),
					selected_cs.getId()
				);
				updateTreeTable();
			}
		});
		add_component.getItems().addAll(central_unit, screen, mouse, keyboard);

		MenuItem details_component = new MenuItem("D�tails");
		details_component.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FXMLLoader detail_loader = new FXMLLoader();
				detail_loader.setLocation(getClass().getResource("/application/detail_station.fxml"));

				Scene detail_loader_scene;

				try {
					detail_loader.setController(
						new DetailStationController(
							screen_controller,
							model_it_bdd.getComputerStation(
								selected_cs.getParentClassroom().getParentBuilding().getParentZone().getName(),
								selected_cs.getParentClassroom().getParentBuilding().getName(),
								selected_cs.getParentClassroom().getName(),
								selected_cs.getId()
							)
						)
					);
					detail_loader_scene = new Scene(detail_loader.load());
					Stage stage = new Stage();
					stage.setTitle("D�tails");
					stage.setScene(detail_loader_scene);
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		MenuItem modify_component = new MenuItem("Modifier");
		modify_component.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FXMLLoader edition_loader = new FXMLLoader();
				edition_loader.setLocation(getClass().getResource("/application/edit_station.fxml"));

				Scene edition_loader_scene;

				try {
					edition_loader.setController(
						new EditStationController(
							screen_controller,
							selected_cs,
							model_it_bdd
						)
					);
					edition_loader_scene = new Scene(edition_loader.load());
					Stage stage = new Stage();
					stage.setTitle("Edition unit� centrale");
					stage.setScene(edition_loader_scene);
					stage.showAndWait();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				updateTreeTable();
			}
		});

		MenuItem delete_component = new MenuItem("Supprimer");
		delete_component.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				model_it_bdd.removeComputerStationAt(
					selected_cs.getParentClassroom().getParentBuilding().getParentZone().getName(),
					selected_cs.getParentClassroom().getParentBuilding().getName(),
					selected_cs.getParentClassroom().getName(),
					selected_cs.getId()
				);
				updateTreeTable();
			}
		});

		this.contextMenu.getItems().addAll(add_component, details_component, modify_component, delete_component);
	}
	
	private void initializeComponentContextMenu() {
		this.componentContextMenu = new ContextMenu();
		
		MenuItem details_component = new MenuItem("D�tails");
		details_component.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FXMLLoader detail_loader = new FXMLLoader();
				detail_loader.setLocation(getClass().getResource("/application/detail_component.fxml"));

				Scene detail_loader_scene;

				try {
					detail_loader.setController(
						new DetailComponentController(
							screen_controller,
							selected_component
						)
					);
					detail_loader_scene = new Scene(detail_loader.load());
					Stage stage = new Stage();
					stage.setTitle("D�tails");
					stage.setScene(detail_loader_scene);
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		MenuItem modify = new MenuItem("Modifier");
		modify.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Modifier mat�riel");
				
				FXMLLoader edition_loader = new FXMLLoader();
				edition_loader.setLocation(getClass().getResource("/application/edit_component.fxml"));

				Scene edition_loader_scene;
				
				try {
					edition_loader.setController(new EditComponentController(screen_controller, selected_component, model_it_bdd));
					edition_loader_scene = new Scene(edition_loader.load());
					Stage stage = new Stage();
					stage.setTitle("Edition mat�riel");
					stage.setScene(edition_loader_scene);
					stage.showAndWait();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				selected_component = null;
				
				updateTreeTable();
			}
		});
		
		MenuItem delete = new MenuItem("Supprimer");
		delete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Supprimer mat�riel");
				selected_component.getParentComputerStation().deleteComponent(selected_component);
				selected_component = null;
		
				updateTreeTable();
			}
		}
	);

	this.componentContextMenu.getItems().addAll(details_component,modify,delete);
	
	}

	private void initializeTeacherTreeTable() {
		this.teacherFirstNameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<User, String>("firstName"));
		this.teacherNameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<User, String>("name"));
		this.teacherDepartementColumn
				.setCellValueFactory(new TreeItemPropertyValueFactory<User, Departement>("departement"));
		
		this.TreeTableTeachers.setOnMouseClicked((MouseEvent e) -> {
			TreeItem<User> selectedItem = (TreeItem<User>) this.TreeTableTeachers.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				if (e.getButton() == MouseButton.PRIMARY) {
					teacherContextMenu.hide();
				}
				else {
					if (e.getButton() == MouseButton.SECONDARY) {
						this.selected_user = selectedItem.getValue();
						if (!this.selected_user.isAdmin()) {
							teacherContextMenu.show(this.TreeTableTeachers, e.getScreenX(), e.getScreenY());
						}
						else {
							teacherContextMenu.hide();
						}
					}
				}
				
			}
		});

		this.updateTeacherTreeTable(this.model_user_bdd.getUserDB());
	}

	private void updateTeacherTreeTable(List<User> list_users) {
		TreeItem<Component> itemRoot = null;
		this.TreeTableTeachers.setRoot(new TreeItem<>());

		for (User user : list_users) {
			this.TreeTableTeachers.getRoot().getChildren().add(new TreeItem<User>(user));
		}
	}

	private void initializeTeacherContextMenu() {
		this.teacherContextMenu = new ContextMenu();
		
		MenuItem modify = new MenuItem("Modifier");
		modify.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Modifier utilisateur");
				
				FXMLLoader edition_loader = new FXMLLoader();
				edition_loader.setLocation(getClass().getResource("/application/edit_teacher.fxml"));

				Scene edition_loader_scene;
				
				try {
	
					edition_loader.setController(new EditTeacherController(screen_controller, selected_user));
					edition_loader_scene = new Scene(edition_loader.load());
					Stage stage = new Stage();
					stage.setTitle("Edition enseignant");
					stage.setScene(edition_loader_scene);
					stage.showAndWait();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				selected_user = null;
				
				updateTeacherTreeTable(model_user_bdd.getUserDB());
				
			}
		});
		
		MenuItem delete = new MenuItem("Supprimer");
		delete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Supprimer utilisateur");
				model_user_bdd.deleteUser(selected_user);
				selected_user = null;
		
				updateTeacherTreeTable(model_user_bdd.getUserDB());
			}
		}
	);

	this.teacherContextMenu.getItems().addAll(modify,delete);
	
	}
	
	private void initializeBookingContextMenu() {
		this.bookingContextMenu = new ContextMenu();
		
		MenuItem delete = new MenuItem("Supprimer");
		delete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Supprimer la r�servation");
				model_it_bdd.deleteBooking(selected_booking);
				selected_booking = null;
				updateBookingTable();
			}
		}
	);

	this.bookingContextMenu.getItems().addAll(delete);
	
	}
	
	private void initializeBookingStationContextMenu() {
		this.bookingStationContextMenu = new ContextMenu();
		
		MenuItem delete = new MenuItem("Supprimer");
		delete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Supprimer station de r�servation");
				model_it_bdd.deleteBookingStation(selected_booking, selected_station_booking);
				selected_station_booking = null;
				updateBookingTable();
			}
		}
	);

	this.bookingStationContextMenu.getItems().addAll(delete);
	}

	private void initializeTreeTable() {
		this.componentNameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Component, String>("name"));
		this.componentAccesibilityColumn
				.setCellValueFactory(new TreeItemPropertyValueFactory<Component, Availability>("access"));
		this.componentStateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Component, State>("state"));

		this.TreeTableMaterial.setOnMouseClicked((MouseEvent e) -> {
			TreeItem<Component> selectedItem = (TreeItem<Component>) this.TreeTableMaterial.getSelectionModel().getSelectedItem();
			if (selectedItem == null) {
				b_reserve.setDisable(true);
			}
			else {
				if (e.getButton() == MouseButton.PRIMARY) {
					if (selectedItem.getValue().getType() == ComponentType.COMPUTER_STATION) {
						b_reserve.setDisable(false);
						this.selected_cs = (ComputerStation) selectedItem.getValue();
					}
					else {
						this.selected_component = (Component) selectedItem.getValue();
						b_reserve.setDisable(true);
					}
					contextMenu.hide();
					componentContextMenu.hide();
				}
				else {
					if (e.getButton() == MouseButton.SECONDARY) {
						if (selectedItem.getValue().getType() == ComponentType.COMPUTER_STATION) {
							b_reserve.setDisable(false);
							this.selected_cs = (ComputerStation) selectedItem.getValue();
							if (this.main_user.isAdmin()) {
								componentContextMenu.hide();
								contextMenu.show(this.TreeTableMaterial, e.getScreenX(), e.getScreenY());
							}
						}
						else {
							b_reserve.setDisable(true);
							this.selected_component = (Component) selectedItem.getValue();
							if (this.main_user.isAdmin()) {
								contextMenu.hide();
								componentContextMenu.show(this.TreeTableMaterial, e.getScreenX(), e.getScreenY());
							}
						}
					}
				}
			}
		});

		this.updateTreeTable();
	}

	private void updateTreeTable(List<ComputerStation> cs) {

		TreeItem<Component> itemRoot = null;
		this.TreeTableMaterial.setRoot(new TreeItem<>());

		for (ComputerStation computerStation : cs) {
			itemRoot = new TreeItem<Component>(computerStation);
			this.TreeTableMaterial.getRoot().getChildren().add(itemRoot);

			for (Component component : computerStation.getComponents()) {
				TreeItem<Component> itemComponent = new TreeItem<Component>(component);
				itemRoot.getChildren().add(itemComponent);
			}
		}
	}

	public void updateTreeTable() {
		if (this.selected_zone == null) {
			this.updateTreeTable(this.model_it_bdd.getAllComputerStations());
		} else if (this.selected_building == null) {
			this.updateTreeTable(this.model_it_bdd.getComputerStationByZone(this.selected_zone));
		} else if (this.selected_classroom == null) {
			this.updateTreeTable(
					this.model_it_bdd.getComputerStationByBuilding(this.selected_zone, this.selected_building));
		} else {
			this.updateTreeTable(this.model_it_bdd.getComputerStationByClassroom(this.selected_zone,
					this.selected_building, this.selected_classroom));
		}
	}
	
	public void updateBookingTable(List<Booking> list_booking) {
		TreeItem<Object> itemRoot = null;
		this.TreeReservation.setRoot(new TreeItem<>());
		
		for (Booking booking : list_booking) {
			itemRoot = new TreeItem<Object>(booking);
			this.TreeReservation.getRoot().getChildren().add(itemRoot);

			for (ComputerStation station : booking.getComputerStations()) {
				TreeItem<Object> itemComponent = new TreeItem<Object>(station);
				itemRoot.getChildren().add(itemComponent);
			}
		}
	}
	
	public void updateBookingTable() {
		this.updateBookingTable(this.model_it_bdd.getUserBookings(this.main_user));
	}
	
	private void initializeBookingTable() {
		//this.TreeReservation.setCellFactory(new TreeItemPropertyValueFactory<>("name"));
		
		this.TreeReservation.setOnMouseClicked((MouseEvent e) -> {
			TreeItem<Object> selectedItem = (TreeItem<Object>) this.TreeReservation.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				if (e.getButton() == MouseButton.PRIMARY) {
					bookingContextMenu.hide();
					bookingStationContextMenu.hide();
				}
				else {
					if (e.getButton() == MouseButton.SECONDARY) {
						if (selectedItem.getValue().getClass() == Booking.class) {
							this.selected_booking = (Booking) selectedItem.getValue();
							bookingContextMenu.show(this.TreeReservation, e.getScreenX(), e.getScreenY());
							bookingStationContextMenu.hide();
						}
						else {
							this.selected_booking = (Booking) selectedItem.getParent().getValue();
							this.selected_station_booking = (ComputerStation) selectedItem.getValue();
							bookingStationContextMenu.show(this.TreeReservation, e.getScreenX(), e.getScreenY());
							bookingContextMenu.hide();
						}
					}
				}
			}
		});
		
		this.updateBookingTable();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO set ITManagementDB as model in order to send request to DB as this
		// moment to complete the scheme

		this.model_it_bdd.addZone(new Zone(1, "Sevenans"));
		this.model_it_bdd.addZone(new Zone(2, "Belfort"));
		this.model_it_bdd.addZone(new Zone(3, "Montb�liard"));

		Building pont = new Building(1, this.model_it_bdd.getZoneByName("Sevenans"), "Pont");
		pont.createNClassroom(2);
		Building tour = new Building(2, this.model_it_bdd.getZoneByName("Sevenans"), "Tour");
		tour.createNClassroom(2);

		Building batA = new Building(1, this.model_it_bdd.getZoneByName("Belfort"), "Bat A");
		batA.createNClassroomWithNComputerStation(2, 3);
		Building batB = new Building(2, this.model_it_bdd.getZoneByName("Belfort"), "Bat B");
		batB.createNClassroomWithNComputerStation(2, 3);
		Building batC = new Building(3, this.model_it_bdd.getZoneByName("Belfort"), "Bat C");
		batC.createNClassroomWithNComputerStation(2, 3);
		Building batD = new Building(4, this.model_it_bdd.getZoneByName("Belfort"), "Bat D");
		batD.createNClassroomWithNComputerStation(2, 3);
		
		Building mecanique = new Building(1, this.model_it_bdd.getZoneByName("Montb�liard"), "M�canique");
		mecanique.createNClassroomWithNComputerStation(2, 3);
		
		ComboZone.getItems().addAll(this.model_it_bdd.getAllZone());
		ComboZone.getItems().add(null);
		
		this.ComboZoneUserDepartement.getItems().addAll(Departement.values());
		this.ComboZoneUserDepartement.getItems().add(null);

		this.initializeTreeTable();
		this.initializeTeacherTreeTable();
		this.initializeContextMenu();
		this.initializeComponentContextMenu();
		this.initializeBookingContextMenu();
		this.initializeBookingStationContextMenu();
		this.initializeTeacherContextMenu();
	}
}
