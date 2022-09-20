package com.diego.api.service;

import com.diego.api.client.messages.facebook.FacebookClient;
import com.diego.api.client.messages.facebook.model.MessageDTO;
import com.diego.api.client.messages.facebook.model.ResponseDTO;
import com.diego.api.client.messages.facebook.model.UserDTO;
import com.diego.api.client.messages.facebook.model.response.UserMessagesDTO;
import com.diego.api.client.messages.facebook.model.request.RequestMessengerDTO;
import com.diego.api.client.messages.facebook.model.response.UserResponseDTO;
import com.diego.api.mapper.facebook.response.ToUserResponseDTO;
import com.diego.api.repositories.models.MessageModel;
import com.diego.api.repositories.models.UserModel;
import com.diego.api.repositories.MessageRepository;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FacebookService implements MessageService {

    Logger logger = LoggerFactory.getLogger(FacebookService.class);

    FacebookClient facebookClient;
    MessageRepository messageRepository;

    public FacebookService(FacebookClient facebookClient, MessageRepository messageRepository) {
        logger.info("*-*-*-*-*-*-*--**-*-*-*-*-*-*-*-*-*-*-*-Creando el constructor de FacebookService-*-**--**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

        this.facebookClient = facebookClient;
        this.messageRepository = messageRepository;
    }

    // Mostrar los usuarios que han escrito a la página de facebook
    public ArrayList<UserResponseDTO> showUsers() {

        ResponseDTO response = facebookClient.getUsers(); //obtenemos los usuarios que facebook nos da
        ArrayList<UserResponseDTO> users = ToUserResponseDTO.toUsersDTO(response);

        return users;
    }

    @Override
    public void sendMessage(Object to, String message) {
        //Debe llamarse desde fuera
        //MessageDTO mensajeFacebook = new MessageDTO();
        UserModel user = (UserModel) to;
        String psid = user.getPsid();
        facebookClient.sendMessage(psid, message);

    }
    
    //ver mensajes de un usuario
    public UserMessagesDTO showMessages(UserModel usuario) {
        //UsuarioModel usuario = userService.findUser(userId);

        String idConversacion = usuario.getIdConversacion();

        UserMessagesDTO response = facebookClient.verMensajesUsuario(idConversacion);
        return response;
    }

    public MessageModel mapMessage(RequestMessengerDTO message) {
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
