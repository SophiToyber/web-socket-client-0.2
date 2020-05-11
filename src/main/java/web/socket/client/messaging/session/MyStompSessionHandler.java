package web.socket.client.messaging.session;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import appearance.application.ui.connectors.ConnectRoomMessageController;
import appearance.application.ui.creators.CreateRoomMessageController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.socket.message.Message;

@Slf4j
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

	private Message message;

	public static CreateRoomMessageController actualCreateControllerObj = CreateRoomMessageController.messageController;
	public static ConnectRoomMessageController actualConnectControllerObj = ConnectRoomMessageController.messageController;
	// As you can see, the class is an extension from the Stom Sesion Adapter class
	// which allows us to override two main and one side methods

	private void subscribeTopic(StompSession session, String endpoint) {
		log.info(String.format("Endpoont was receive: %s", endpoint));
		session.subscribe(endpoint, new StompFrameHandler() {
			@Override
			public Type getPayloadType(StompHeaders headers) {
				return Message.class;
			}

			// The method receives the object sent to it by the previous method, it is used
			// directly to display messages
			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				Message msg = (Message) payload;
				log.info(String.format("Received + %s", msg.getText()));
				try {
				actualCreateControllerObj.messageList
						.appendText(String.format("%s: %s \n", msg.getFrom(), msg.getText()));
				}
				catch(Exception e){
				actualConnectControllerObj.messageList
						.appendText(String.format("%s: %s \n", msg.getFrom(), msg.getText()));
				}
			}
		});
	}

	// This method call subscribe topic method
	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		System.out.println(String.format("User: %s - connected", message.getFrom()));
		subscribeTopic(session, "/topic/messages");
	};

}
