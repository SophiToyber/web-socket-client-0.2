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
		changeScene("fxml/CreateRoom.fxml", event);
	}

	public void conectToRoom(ActionEvent event) throws IOException {
		changeScene("fxml/ConnectToRoom.fxml", event);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
