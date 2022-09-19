package com.diego.api.service;

import com.diego.api.client.WhatsappClient;
import com.diego.api.dto.facebook_manager.ResponseDTO;
import com.diego.api.dto.whatsapp_manager.message_default.RequestDTO;
import com.diego.api.dto.whatsapp_manager.response_dto.ResponseWhatsappMessageDTO;
import com.diego.api.dto.whatsapp_manager.personalized_message.PersonalizedMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsappService {

    Logger logger = LoggerFactory.getLogger(WhatsappService.class);

    @Value("${token.page}")
    private String token;

    RestTemplate restTemplate;
    WhatsappClient whatsappClient;

    public WhatsappService(WhatsappClient whatsappClient) {
        logger.info("El token page es: " + token);
        this.whatsappClient = whatsappClient;
    }

    public ResponseEntity<ResponseDTO> sendDefaultMessage(RequestDTO request) {
        logger.info("El token page es: " + token);
        return whatsappClient.sendDefaultMessage(request);
    }

    public void sendMessage(PersonalizedMessageDTO mensaje) {

        whatsappClient.sendMessage(mensaje);
    }

    public ResponseWhatsappMessageDTO mapRequest(ResponseWhatsappMessageDTO mensaje) {
        return mensaje;
    }

}
