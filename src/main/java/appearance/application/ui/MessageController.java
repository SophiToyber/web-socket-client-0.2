package appearance.application.ui;

import static appearance.application.ui.ConnectRoomController.getClientFromConnectRoomController;
import static appearance.application.ui.CreateRoomController.getClientFromCreateRoomController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import appearance.application.ControllersConfiguration;
import appearance.application.ui.interfaces.IAllert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import web.socket.client.Client;
import web.socket.client.messaging.config.WebSocketClientConfig;
import web.socket.message.Message;

@Slf4j
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MessageController extends ControllersConfiguration implements IAllert {
	
	@FXML
	TextArea messageList;

	@FXML
	TextArea message;

	public void initialize() {
		// JavaFX initialization phase
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {

	}

	@FXML
	public void sendMessage(ActionEvent event) throws IOException, InterruptedException, ExecutionException {
		WebSocketClientConfig connector = new WebSocketClientConfig();
		Client client;
		try {
			client = getClientFromConnectRoomController();
		} catch (Exception e) {
			client = getClientFromCreateRoomController();
		}
		
		log.info(String.format("ACTUAL USER IS: %s", client));
		messageList.appendText(String.format("You %s: %s \n", client.getName(), message.getText()));
		connector.configureWebsocket(client).send(String.format("/app/chat/%s", client.getTopic()),
				Message.builder().from(client.getName()).text(message.getText()).build());
		message.clear();
	}

	public static void updateMessageList(Message msg) {
		log.error(msg.toString());
	}

}
