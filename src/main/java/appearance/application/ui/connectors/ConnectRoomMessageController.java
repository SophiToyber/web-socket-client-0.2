package appearance.application.ui.connectors;


import static appearance.application.ui.ConnectRoomController.getClientFromConnectRoomController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import appearance.application.ControllersConfiguration;
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
public class ConnectRoomMessageController extends ControllersConfiguration implements IAllert {
	
	@FXML
	public TextArea messageList;

	@FXML
	public TextArea message;
	
	public static ConnectRoomMessageController messageController ;
	public static WebSocketClientConfig connector = new WebSocketClientConfig();

	public void initialize() {
		// JavaFX initialization phase
		messageController=this;
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
	}
	
	@FXML
	public void sendMessage(ActionEvent event) throws IOException, InterruptedException, ExecutionException {
		Client client;
		try {
			client = getClientFromConnectRoomController();
		} catch (Exception e) {
			client = getClientFromConnectRoomController();
			log.error("Error");
		}
		connector.configureWebsocket(client).send(String.format("/app/chat/%s", client.getTopic()),
				Message.builder().from(client.getName()).text(message.getText()).build());
		message.clear();
		
	}
	

}