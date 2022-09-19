package com.diego.api.client;

import com.diego.api.dto.facebook_manager.ResponseDTO;
import com.diego.api.dto.whatsapp_manager.message_default.RequestDTO;
import com.diego.api.dto.whatsapp_manager.personalized_message.PersonalizedMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WhatsappClient {

    Logger logger = LoggerFactory.getLogger(WhatsappClient.class);

    RestTemplate restTemplate;

    // Identificador de acceso temporal
    private String token = "EAAInUz9K7mcBANbTrFQfi8OH5HdYJ8RL56JQNJrMpfblFQ4LYv7A9ZBzBAf3hrI1ZCff7lZBCg7ZArZAnLPbO8DiYRHJ6sCZCffEQT15n5jlPAmezkOVx6kBkMIS83MTZCRgt6tJsVACxOZBZApaWgsEY2UU4q5XivQlc4K3G3MBEj3TzlOLPvqBxZCqZAY3g0zdxwGxR3OqAZCZBogZDZD";
    private String baseURL = "https://graph.facebook.com/v14.0/105390802318250/messages";

    public WhatsappClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<ResponseDTO> sendDefaultMessage(RequestDTO request) {

        String url = baseURL;
        String token = this.token;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        ResponseEntity<ResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, ResponseDTO.class);

        return response;
    }

    public void sendMessage(PersonalizedMessageDTO mensaje) {
        String url = baseURL;
        String token2 = this.token;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token2);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(mensaje.toString(), headers);

        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

    }
}
