package appearance.application.ui.connectors;

import static appearance.application.ui.connectors.ConnectRoomController.getClientFromConnectRoomController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.springframework.messaging.simp.stomp.StompSession;

import appearance.application.configuration.ControllersConfiguration;
import appearance.application.ui.interfaces.IAllert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import lombok.extern.slf4j.Slf4j;
import web.socket.client.Client;
import web.socket.client.messaging.config.WebSocketClientConfig;
import web.socket.message.Message;

@Slf4j
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ConnectRoomMessageController extends ControllersConfiguration implements IAllert {

	public static ConnectRoomMessageController messageController;
	public static Client client;

	private WebSocketClientConfig connector = new WebSocketClientConfig();
	private StompSession session;
	private ActionEvent actionEvent;

	@FXML
	public TextArea messageList;

	@FXML
	public TextArea message;

	public void initialize() {
		// JavaFX initialization phase
		messageController = this;
		try {
			client = getClientFromConnectRoomController();
		} catch (Exception e) {
			client = null;
			log.error("Error");
		}
		try {
			session = connector.configureWebsocket(client);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
	}

	@FXML
	public void sendMessage(ActionEvent event) throws IOException, InterruptedException, ExecutionException {
		try {
			session.send(String.format("/app/chat/%s", client.getTopic()),
					Message.builder().from(client.getName()).text(message.getText()).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		message.clear();
		this.actionEvent = event;
	}

	@FXML
	private void handleOnKeyReleased(KeyEvent event) {
		log.info(String.format("KEY ON PRESS %s", event.getText()));
		if (event.getText().equals("")) {
			try {
				changeScene("fxml/Start.fxml", actionEvent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("EXIT WAS PRESSED");
		}
		if (event.getText().equals("`") || event.getText().equals("'")) {
			showAlert(Alert.AlertType.INFORMATION, ((Node) actionEvent.getSource()).getScene().getWindow(), "Ifo",
					"Program was created by Prosvitlyuk Mikhailo");
		}
	}

}