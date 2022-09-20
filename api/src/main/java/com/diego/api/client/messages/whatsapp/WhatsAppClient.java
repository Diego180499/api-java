package com.diego.api.client.messages.whatsapp;

import com.diego.api.client.messages.facebook.model.ResponseDTO;
import com.diego.api.client.messages.whatsapp.model.personalized_message.PersonalizedMessageDTO;
import com.diego.api.dto.whatsapp_manager.message_default.RequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WhatsAppClient {

    Logger logger = LoggerFactory.getLogger(WhatsAppClient.class);

    private RestTemplate restTemplate;
    private WhatsAppConfig whatsAppConfig;

    public WhatsAppClient(RestTemplate restTemplate, WhatsAppConfig whatsAppConfig) {
        this.restTemplate = restTemplate;
        this.whatsAppConfig = whatsAppConfig;
    }

    public ResponseEntity<ResponseDTO> sendDefaultMessage(RequestDTO request) {
        String url = whatsAppConfig.getBaseURL();
        String token = whatsAppConfig.getToken();
        logger.info("++++++++++++++++++++++El token de whatsapp es: " + token);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        ResponseEntity<ResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, ResponseDTO.class);

        return response;
    }

    public void sendMessage(PersonalizedMessageDTO mensaje) {
        String url = whatsAppConfig.getBaseURL();
        String token = whatsAppConfig.getToken();
        logger.info("++++++++++++++++++++++El token de whatsapp es: " + token);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(mensaje.toString(), headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}
