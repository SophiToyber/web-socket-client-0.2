package web.socket.client.messaging.config;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import web.socket.client.Client;
import web.socket.client.messaging.session.MyStompSessionHandler;
import web.socket.message.Message;

@Component
public class WebSocketClientConfig {

	// @Value( "${url.main}" )
	private final String URL = "ws://localhost:8086/messages";

	private WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
	private List<Transport> transports = Arrays.asList(new WebSocketTransport(simpleWebSocketClient));

	// SockJs like subprotocol Websockets, he responsible for delivering messages
	// directly
	private SockJsClient sockJsClient = new SockJsClient(transports);
	private WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

	public StompSession configureWebsocket(Client client) throws InterruptedException, ExecutionException {

		// The config first creates a set of transport protocols, among which we specify
		// a web socket
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		// Next, we establish a communication session on an already configured channel
		StompSessionHandler sessionHandler = new MyStompSessionHandler(
				Message.builder().from(client.getName()).text("default").fromTopic(client.getTopic()).build());
		StompSession session = stompClient.connect(URL, sessionHandler).get();
		return session;
	}

}
