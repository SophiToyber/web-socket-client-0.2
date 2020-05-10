package appearance.application.ui;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import appearance.application.ControllersConfiguration;
import appearance.application.ui.interfaces.IAllert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
				showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(), "Success",
						String.format("Information about creating: %s", roomStatus));
			}

			changeScene("fxml/Messaging.fxml", event);
			
			log.error(client.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
			showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(), "Sorry :(",
					e.getMessage());
		}
	}
}
