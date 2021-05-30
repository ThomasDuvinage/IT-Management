module IT_Management {
	requires javafx.controls;
	requires javafx.fxml;
	
	exports User;
	exports Component;
	exports guiController;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
}
