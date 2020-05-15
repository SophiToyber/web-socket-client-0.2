package appearance.application.ui.interfaces;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public interface IAllert {
	
	public final String DARK_THEME = "-fx-background-color: black;" +"-fx-text-fill: white;";
	
	public final String WHITE_THEME = "-fx-background-color: white;" +"-fx-text-fill: black;";

	public default void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
	
}
