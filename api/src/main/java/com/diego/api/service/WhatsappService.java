package com.diego.api.service;

import com.diego.api.client.messages.whatsapp.WhatsAppClient;
import com.diego.api.client.messages.facebook.model.ResponseDTO;
import com.diego.api.client.messages.whatsapp.model.personalized_message.PersonalizedMessageDTO;
import com.diego.api.client.messages.whatsapp.model.response_dto.ResponseWhatsappMessageDTO;
import com.diego.api.dto.whatsapp_manager.message_default.RequestDTO;
import com.diego.api.repositories.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsappService implements MessageService {

    Logger logger = LoggerFactory.getLogger(WhatsappService.class);

    @Value("${token.page}")
    private String token;

    RestTemplate restTemplate;
    WhatsAppClient whatsappClient;

    public WhatsappService(WhatsAppClient whatsappClient) {
        logger.info("El token page es: " + token);
        this.whatsappClient = whatsappClient;
    }

    public ResponseEntity<ResponseDTO> sendDefaultMessage(RequestDTO request) {
        logger.info("El token page es: " + token);
        return whatsappClient.sendDefaultMessage(request);
    }

    public ResponseWhatsappMessageDTO mapRequest(ResponseWhatsappMessageDTO mensaje) {
        return mensaje;
    }

    @Override
    public void sendMessage(Object to, String message) {
        // el to será el Objecto Usuario a quien le enviaré el mensaje
        UserModel usuario = (UserModel) to;
        PersonalizedMessageDTO mensajeWhatsapp = new PersonalizedMessageDTO();

        if ((usuario.getId_whatsapp() == null) && usuario.getTelefono() != null) {
            mensajeWhatsapp.setNumero(usuario.getExtension() + usuario.getTelefono());
        } else {
            mensajeWhatsapp.setNumero(usuario.getId_whatsapp());
        }

        mensajeWhatsapp.setMensaje(message);
        whatsappClient.sendMessage(mensajeWhatsapp);

    }

}
