package com.diego.api.client;

import com.diego.api.dto.facebook_manager.ResponseDTO;
import com.diego.api.dto.whatsapp_manager.message_default.RequestDTO;
import com.diego.api.dto.whatsapp_manager.personalized_message.PersonalizedMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WhatsappClient {

    Logger logger = LoggerFactory.getLogger(WhatsappClient.class);

    @Value("${token.whatsapp}")
    private String token;

    @Value("${url.baseUrl}")
    private String baseURL;

    RestTemplate restTemplate;

    // Identificador de acceso temporal
    public WhatsappClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<ResponseDTO> sendDefaultMessage(RequestDTO request) {
        logger.info("++++++++++++++++++++++El token de whatsapp es: " + token);
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
        logger.info("++++++++++++++++++++++El token de whatsapp es: " + token);
        String url = baseURL;
        String token2 = this.token;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token2);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(mensaje.toString(), headers);

        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

    }
}
