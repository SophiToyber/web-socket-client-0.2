package web.socket.client.messaging.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import web.socket.client.connecting.option.ServerConnector;
import web.socket.client.connecting.service.ServerConnectionService;

@Configuration
public class AppConfig {

	@Bean
	public ServerConnector severConnector() {
		return new ServerConnector();
	}
	
	@Bean
	public ServerConnectionService serverConnectionService() {
		return new ServerConnectionService(severConnector());
	}

	@Bean
	public WebSocketClientConfig webSocketClientConfig(){
		return new WebSocketClientConfig();
	}

}
