package appearance.application.ui.connectors;

import java.io.IOException;

import javax.annotation.PostConstruct;

import appearance.application.configuration.ControllersConfiguration;
import appearance.application.ui.interfaces.IAllert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lombok.extern.slf4j.Slf4j;
import web.socket.client.Client;
import web.socket.client.connecting.option.ServerConnector;
import web.socket.client.connecting.service.ServerConnectionService;

@Slf4j
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ConnectRoomController extends ControllersConfiguration implements IAllert {

	public static Client client;

	@FXML
	private TextField nameTextLine;
	@FXML
	private TextField expectedRoomTextLine;

	@FXML
	private Button checkButton;

	public void initialize() {
		// JavaFX initialization phase
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {

	}

	public static Client getClientFromConnectRoomController() {
		return client;
	}

	@FXML
	public void checkRoom(ActionEvent event) throws IOException {
		ServerConnectionService service = new ServerConnectionService(new ServerConnector());
		try {
			client = Client.builder().name(nameTextLine.getText()).topic("default")
					.expectedRoom(expectedRoomTextLine.getText()).build();

			String connectingResult = service.sendCreateConnectionRequestAndGetTopic(client);
			client.setTopic(connectingResult);

			if (!connectingResult.equals("empty")) {
				try {
					changeScene("fxml/ConnectRoomMessaging.fxml", event);
					showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(), "Success",
							String.format("Connection active \n Your topic is: %s", connectingResult));
				} catch (Exception e) {
					log.error("ERROR CHANGE SCENE --" + e.getMessage());
				}
			}
		} catch (Exception e) {
			showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(), "Sorry :(",
					"Room is not created or you write incorrect RoomName");
		}
	}

	@FXML
	public void onConnectMouseEntered() throws IOException {
		checkButton.setStyle(DARK_THEME);
	}

	@FXML
	public void onСonnectMouseExited() throws IOException {
		checkButton.setStyle(WHITE_THEME);
	}
	
}
