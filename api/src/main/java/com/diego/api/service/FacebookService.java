package com.diego.api.service;

import com.diego.api.client.messages.facebook.FacebookClient;
import com.diego.api.client.messages.facebook.model.response.in.show_users.ResponseDTO;
import com.diego.api.client.messages.facebook.model.response.in.messages_user.UserMessagesDTO;
import com.diego.api.controllers.client.facebook.request.notifyMessage.RequestMessengerDTO;
import com.diego.api.client.messages.facebook.model.response.out.show_user.UserResponseDTO;
import com.diego.api.mapper.facebook.response.ToUserResponseDTO;
import com.diego.api.repositories.models.MessageModel;
import com.diego.api.repositories.models.UserModel;
import com.diego.api.repositories.MessageRepository;
import com.diego.api.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FacebookService implements MessageService {

    Logger logger = LoggerFactory.getLogger(FacebookService.class);

    FacebookClient facebookClient;
    MessageRepository messageRepository;
    UserRepository userRepository;

    public FacebookService(FacebookClient facebookClient, MessageRepository messageRepository, UserRepository userRepository) {
        logger.info("*-*-*-*-*-*-*--**-*-*-*-*-*-*-*-*-*-*-*-Creando el constructor de FacebookService-*-**--**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

        this.facebookClient = facebookClient;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    // Mostrar los usuarios que han escrito a la página de facebook
    public ArrayList<UserResponseDTO> showUsers() {

        ResponseDTO response = facebookClient.getUsers(); //obtenemos los usuarios que facebook nos da
        ArrayList<UserResponseDTO> users = ToUserResponseDTO.toUsersDTO(response);
        updateUsers(users);

        return users;
    }

    @Override
    public void sendMessage(Object to, String message) {
        //Debe llamarse desde fuera
        UserModel user = (UserModel) to;
        String psid = user.getPsid();
        facebookClient.sendMessage(psid, message);

    }

    //ver mensajes de un usuario
    public UserMessagesDTO showMessages(Integer userId) {
        
        Optional<UserModel> user = userRepository.findById(userId);
        UserModel userModel = user.get();
        
        String idConversacion = userModel.getIdConversacion();

        UserMessagesDTO response = facebookClient.verMensajesUsuario(idConversacion);

        return response;
    }

    public MessageModel mapMessage(RequestMessengerDTO message) {
        logger.info("*-*-*-*-*-*-*-Mapeando el mensaje recibido*-*-*-*-*-*-*-");
        Integer id = 0;
        String psid = message.getEntry().get(0).getMessaging().get(0).getSender().getId();
        String mensaje = message.getEntry().get(0).getMessaging().get(0).getMessage().getText();

        MessageModel messageModel = new MessageModel(id, psid, mensaje);

        return messageModel;
    }

    public void verifyUser(RequestMessengerDTO mensaje) {

        String nickName = mensaje.getEntry().get(0).getMessaging().get(0).getMessage().getText();
        String psid = mensaje.getEntry().get(0).getMessaging().get(0).getSender().getId();

        logger.info("mensaje recibido de : " + psid);
        logger.info("***MENSAJE: " + nickName);

        UserModel user = findUserWithNickName(nickName);

        if (user == null) {
            facebookClient.sendMessage(psid, "Para iniciar un chat, debes ingresar tu NickName, si no tienes uno Registrate");
        } else {
            facebookClient.sendMessage(psid, "Hola " + user.getNombre() + ", es un gusto saludarte");
            updateUser(psid, user);
        }

    }

    public UserModel findUserWithNickName(String nickName) {

        ArrayList<UserModel> users = (ArrayList<UserModel>) userRepository.findAll();

        for (UserModel user : users) {
            if (user.getNickName().equals(nickName)) {
                return user;
            }
        }

        return null;
    }

    public void updateUser(String psid, UserModel userModel) {
        userModel.setPsid(psid);
        userRepository.save(userModel);
    }

    public void updateUsers(ArrayList<UserResponseDTO> users) {

        ArrayList<UserModel> usersModel = (ArrayList<UserModel>) userRepository.findAll();

        for (UserResponseDTO user : users) {
            for (UserModel userModel : usersModel) {
                if (user.getPsid().equals(userModel.getPsid())) {
                    userModel.setIdConversacion(user.getIdConversacion());
                    userRepository.save(userModel);
                }
            }
        }
    }

    public void saveMessage(MessageModel mensaje) {
        messageRepository.save(mensaje);
    }

}
