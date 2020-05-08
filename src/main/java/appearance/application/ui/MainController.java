package appearance.application.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import appearance.application.ControllersConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController extends ControllersConfiguration implements Initializable {

	// Spring Injection

	// JavaFX Injection

	@FXML
	public void initialize() {
		// JavaFX initialization phase
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
	}

	public void createRoom(ActionEvent event) throws IOException {

		Scene tableViewScene = new Scene(loadView("fxml/CreateRoom.fxml").getView());
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.setResizable(false);
		window.show();
	}

	public void conectToRoom(ActionEvent event) throws IOException {
		Scene tableViewScene = new Scene(loadView("fxml/ConnectToRoom.fxml").getView());
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.setResizable(false);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
