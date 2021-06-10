module IT_Management {
	requires javafx.controls;
	requires javafx.fxml;
	
	exports User;
	exports Component;
	exports guiController;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens guiController to javafx.graphics, javafx.fxml;
}