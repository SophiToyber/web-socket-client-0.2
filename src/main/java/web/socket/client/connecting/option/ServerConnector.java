package web.socket.client.connecting.option;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NoArgsConstructor;
import web.socket.client.Client;

@Component
public class ServerConnector {

	private final RestTemplate restTemplate = new RestTemplate();
	private final HttpHeaders headers = new HttpHeaders();
	private final ObjectMapper objectMapper = new ObjectMapper();

	// This metod just send request on server end return server responce
	public String connect(String URL, Client client) throws JsonProcessingException {
		
		String json = objectMapper.writeValueAsString(client);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		String getClientresponce = restTemplate.postForObject(URL, request, String.class);
		
		return getClientresponce;
	}

}
