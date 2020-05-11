package appearance.application.ui.creators;

import static appearance.application.ui.connectors.ConnectRoomController.getClientFromConnectRoomController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.springframework.messaging.simp.stomp.StompSession;

import appearance.application.configuration.ControllersConfiguration;
import appearance.application.ui.interfaces.IAllert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import lombok.extern.slf4j.Slf4j;
import web.socket.client.Client;
import web.socket.client.messaging.config.WebSocketClientConfig;
import web.socket.message.Message;

@Slf4j
@SuppressWarnings("SpringJavaAutowiringInspection")
public class CreateRoomMessageController extends ControllersConfiguration implements IAllert {

	public static CreateRoomMessageController messageController;
	public static Client client;

	@FXML
	public TextArea messageList;

	@FXML
	public TextArea message;

	private WebSocketClientConfig connector = new WebSocketClientConfig();
	private StompSession session;

	public void initialize() {
		// JavaFX initialization phase
		messageController = this;
		try {
			client = getClientFromConnectRoomController();
		} catch (Exception e) {
			client = getClientFromConnectRoomController();
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

	}

}