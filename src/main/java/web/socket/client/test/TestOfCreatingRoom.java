package web.socket.client.test;

import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import web.socket.client.Client;
import web.socket.client.connecting.option.ServerConnector;
import web.socket.client.connecting.service.ServerConnectionService;
import web.socket.client.messaging.config.WebSocketClientConfig;
import web.socket.message.Message;

@Slf4j
@SpringBootApplication
public class TestOfCreatingRoom {

	private static ServerConnectionService service = new ServerConnectionService(new ServerConnector());
	private static WebSocketClientConfig connector = new WebSocketClientConfig();

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		SpringApplication.run(TestOfCreatingRoom.class, args);
		Client client = Client.builder().name("Misha").topic("duck").expectedRoom("javax").build();

		String connectingResult = service.sendCreateConnectionRequestAndGetTopic(client);
		client.setTopic(connectingResult);
			connector.configureWebsocket(client).send(String.format("/app/chat/%s", client.getTopic()),
					Message.builder().from(client.getName()).text("Fuck u, stupid DUCK").build());
	}
}
