package web.socket.client.connecting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.socket.client.Client;
import web.socket.client.connecting.option.ServerConnector;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServerConnectionService {

	private final String CONNECTION_URL = "http://localhost:8086/create/connection";	
	private final String ROOM_URL = "http://localhost:8086/create/room";
	
	private final ServerConnector connection;

	public String sendCreateRoomRequestAndGetStatus(Client client) {
		try {
			String status = connection.connect(ROOM_URL, client);
			log.info("server created room");
			return status;
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			return String.format("Error: %s ", e.getMessage());
		}
	}

	public String sendCreateConnectionRequestAndGetTopic(Client client) {
		try {
			String receivedЕopic = connection.connect(CONNECTION_URL, client);
			return receivedЕopic;
		} catch (JsonProcessingException e) {
			return String.format("Error: %s ", e.getMessage());
		}
	}

}