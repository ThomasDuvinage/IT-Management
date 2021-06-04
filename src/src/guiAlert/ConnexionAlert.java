package guiAlert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConnexionAlert {
	public ConnexionAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur d'authentification");
		alert.setHeaderText(null);
		alert.setContentText("Nom d'utilisateur ou mot de passe erroné.");
		alert.showAndWait();
	}
}
