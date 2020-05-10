package web.socket.client.messaging.session;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import appearance.application.ui.MessageController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.socket.message.Message;

import static appearance.application.ui.MessageController.updateMessageList;

@Slf4j
@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

	private Message message;

	// As you can see, the class is an extension from the Stom Sesion Adapter class
	// which allows us to override two main and one side methods
	private void subscribeTopic(StompSession session, String endpoint) {

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
				updateMessageList(msg);
				log.info(String.format("Received + %s", msg.getText()));
			}
		});
	}

	// This method just do something after connection
	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		System.out.println(String.format("User: %s - connected", message.getFrom()));
		subscribeTopic(session, "/topic/messages");
	};

}
