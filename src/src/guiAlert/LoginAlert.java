package guiAlert;

import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javafx.util.Pair;

public class LoginAlert {
	public LoginAlert() {
		// code taken from: https://code.makery.ch/blog/javafx-dialogs-official/
		
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Authentification");
		dialog.setHeaderText("Authentification requise");

		// Set the icon (must be included in the project).
		dialog.setGraphic(new ImageView(this.getClass().getResource("/guiAlert/locked_64.png").toString()));

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 20, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Nom d'utilisateur");
		PasswordField password = new PasswordField();
		password.setPromptText("Mot de passe");
		
		Label l_username = new Label("Nom d'utilisateur :");
		Label l_password = new Label("Mot de passe :");

		grid.add(l_username, 0, 0);
		grid.add(username, 1, 0);
		grid.add(l_password, 0, 1);
		grid.add(password, 1, 1);
		
		GridPane.setHalignment(l_username, HPos.RIGHT);
		GridPane.setHalignment(l_password, HPos.RIGHT);
		
		username.setPrefWidth(200);
		password.setPrefWidth(200);

		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(username.getText().toString(), password.getText().toString());
		    }
		    return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
		    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
		});
	}
	
	public void login() {
		
	}
}
