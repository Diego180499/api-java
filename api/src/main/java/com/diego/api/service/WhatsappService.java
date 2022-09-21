package com.diego.api.service;

import com.diego.api.client.messages.whatsapp.WhatsAppClient;
import com.diego.api.client.messages.whatsapp.model.request.out.message_default.MessageDefaultDTO;
import com.diego.api.client.messages.whatsapp.model.request.out.send_message.PersonalizedMessageDTO;
import com.diego.api.client.messages.whatsapp.model.request.in.notify_message.RequestWhatsappMessageDTO;
import com.diego.api.repositories.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    public void sendDefaultMessage(MessageDefaultDTO request) {
        logger.info("El token page es: " + token);
         whatsappClient.sendDefaultMessage(request);
    }

    public RequestWhatsappMessageDTO mapRequest(RequestWhatsappMessageDTO mensaje) {
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
