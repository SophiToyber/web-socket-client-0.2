package appearance.application.ui.creators;

import java.io.IOException;

import javax.annotation.PostConstruct;

import appearance.application.configuration.ControllersConfiguration;
import appearance.application.ui.interfaces.IAllert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import web.socket.client.Client;
import web.socket.client.connecting.option.ServerConnector;
import web.socket.client.connecting.service.ServerConnectionService;

@Slf4j
@SuppressWarnings("SpringJavaAutowiringInspection")
public class CreateRoomController extends ControllersConfiguration implements IAllert {

	@FXML
	private TextField nameTextLine;
	@FXML
	private TextField roomTextLine;

	public static Client client;

	public static Client getClientFromCreateRoomController() {
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
	public void createRoom(ActionEvent event) throws IOException {
		ServerConnectionService service = new ServerConnectionService(new ServerConnector());
		try {
			client = Client.builder().name(nameTextLine.getText()).topic(roomTextLine.getText()).expectedRoom("default")
					.build();
			String roomStatus = service.sendCreateRoomRequestAndGetStatus(client);

			if (roomStatus.equals("created")) {
				try {
					changeScene("fxml/CreateRoomMessaging.fxml", event);
					showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(), "Success",
							String.format("Information about creating: %s", roomStatus));
				} catch (Exception e) {
					log.error(String.format("ERROR CHANGE SCENE -- %s", e.getMessage()));
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(), "Sorry :(",
					e.getMessage());
		}
	}
}
