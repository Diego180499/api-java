package com.diego.api.service;

import com.diego.api.client.FacebookClient;
import com.diego.api.dto.facebook_manager.MessageDTO;
import com.diego.api.dto.facebook_manager.ResponseDTO;
import com.diego.api.dto.facebook_manager.responseDTO.UserMessagesDTO;
import com.diego.api.dto.facebook_manager.webhook.responseMessenger.ResponseMessengerDTO;
import com.diego.api.models.MessageModel;
import com.diego.api.models.UsuarioModel;
import com.diego.api.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {

    Logger logger = LoggerFactory.getLogger(FacebookService.class);

    FacebookClient facebookClient;
    MessageRepository messageRepository;

    public FacebookService(FacebookClient facebookClient, MessageRepository messageRepository) {
        logger.info("*-*-*-*-*-*-*--**-*-*-*-*-*-*-*-*-*-*-*-Creando el constructor de FacebookService-*-**--**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

        this.facebookClient = facebookClient;
        this.messageRepository = messageRepository;
    }

    public ResponseDTO obtenerUsuarios() {

        ResponseDTO response = facebookClient.obtenerUsuarios();
        return response;
    }

    public void enviarMensaje(MessageDTO msj, UsuarioModel usuarioModel) {
        String mensaje = msj.getMensaje();
        String psid = usuarioModel.getPsid();
        facebookClient.enviarMensaje(psid, mensaje);
    }

    //ver mensajes de un usuario
    public UserMessagesDTO verMensajes(UsuarioModel usuario) {
        //UsuarioModel usuario = userService.findUser(userId);

        String idConversacion = usuario.getIdConversacion();

        UserMessagesDTO response = facebookClient.verMensajesUsuario(idConversacion);
        return response;
    }

    public MessageModel mapMessage(ResponseMessengerDTO message) {
        logger.info("*-*-*-*-*-*-*-Mapeando el mensaje recibido*-*-*-*-*-*-*-");
        Integer id = 0;
        String psid = message.getEntry()[0].getMessaging()[0].getSender().getId();
        String mensaje = message.getEntry()[0].getMessaging()[0].getMessage().getText();

        MessageModel messageModel = new MessageModel(id, psid, mensaje);

        return messageModel;
    }

    public void saveMessage(MessageModel mensaje) {
        messageRepository.save(mensaje);
    }

}
