package appearance.application.ui.connectors;

import java.io.IOException;

import javax.annotation.PostConstruct;

import appearance.application.ControllersConfiguration;
import appearance.application.ui.interfaces.IAllert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import web.socket.client.Client;
import web.socket.client.connecting.option.ServerConnector;
import web.socket.client.connecting.service.ServerConnectionService;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class ConnectRoomController extends ControllersConfiguration implements IAllert {

	@FXML
	private TextField nameTextLine;
	@FXML
	private TextField expectedRoomTextLine;

	public static Client client;

	public static Client getClientFromConnectRoomController() {
		return client;
	}

	public void initialize() {
		// JavaFX initialization phase
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {

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
				showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(), "Success",
						String.format("Connection active \n Your topic is: %s", connectingResult));
			}
			changeScene("fxml/ConectRoomMessaging.fxml", event);
		} catch (Exception e) {
			showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(), "Sorry :(",
					"Room is not created or you write incorrect RoomName");
		}
	}

}
