package com.diego.api.service;

import com.diego.api.client.WhatsappClient;
import com.diego.api.dto.facebook_manager.ResponseDTO;
import com.diego.api.dto.whatsapp_manager.message_default.RequestDTO;
import com.diego.api.dto.whatsapp_manager.response_dto.ResponseWhatsappMessageDTO;
import com.diego.api.dto.whatsapp_manager.personalized_message.PersonalizedMessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsappService {

    @Value("${token.page}")
    private String token;

    RestTemplate restTemplate;
    WhatsappClient whatsappClient;

    public WhatsappService(WhatsappClient whatsappClient) {
        this.whatsappClient = whatsappClient;
    }

    public ResponseEntity<ResponseDTO> sendDefaultMessage(RequestDTO request) {
        return whatsappClient.sendDefaultMessage(request);
    }

    public void sendMessage(PersonalizedMessageDTO mensaje) {
        
        whatsappClient.sendMessage(mensaje);
    }

    public ResponseWhatsappMessageDTO mapRequest(ResponseWhatsappMessageDTO mensaje) {
        return mensaje;
    }

}
