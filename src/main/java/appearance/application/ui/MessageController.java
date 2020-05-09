package appearance.application.ui;

import java.io.IOException;
import java.util.Random;

import javax.annotation.PostConstruct;

import appearance.application.ControllersConfiguration;
import appearance.application.ui.interfaces.IAllert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;
import web.socket.client.Client;

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
	public void sendMessage(ActionEvent event) throws IOException {
		CreateRoomController con = new CreateRoomController();
		messageList.appendText(String.format("You %s: %s \n",con.getClient().getName() ,message.getText()));
		message.clear();
	}
	
}
