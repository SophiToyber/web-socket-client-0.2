package appearance.application.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import com.sun.prism.paint.Color;

import appearance.application.configuration.ControllersConfiguration;
import appearance.application.ui.interfaces.IAllert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController extends ControllersConfiguration implements Initializable,IAllert {

	// Spring Injection

	// JavaFX Injection
	@FXML
	Button connectButton;
	
	@FXML
	Button createButton;
	

	@FXML
	public void initialize() {
		// JavaFX initialization phase
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
	}

	@FXML
	public void createRoom(ActionEvent event) throws IOException {
		changeScene("fxml/CreateRoom.fxml", event);
	}

	@FXML
	public void conectToRoom(ActionEvent event) throws IOException {
		
		changeScene("fxml/ConnectToRoom.fxml", event);
	}
	
	
	@FXML
	public void onCrateMouseEntered() throws IOException {
		createButton.setStyle(DARK_THEME);
	}
	
	@FXML
	public void onСreateMouseExited() throws IOException {
		createButton.setStyle(WHITE_THEME);
	}
	
	@FXML
	public void onConnectMouseEntered() throws IOException {
		connectButton.setStyle(DARK_THEME);
	}
	
	@FXML
	public void onСonnectMouseExited() throws IOException {
		connectButton.setStyle(WHITE_THEME);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
