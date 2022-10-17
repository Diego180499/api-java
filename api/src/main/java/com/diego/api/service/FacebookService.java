package com.diego.api.service;

import com.diego.api.client.facebook.FacebookClient;
import com.diego.api.client.facebook.dto.response.ResponseDTO;
import com.diego.api.client.facebook.dto.response.UserMessagesDTO;
import com.diego.api.controllers.facebook.dto.request.RequestMessengerDTO;
import com.diego.api.exception.IdConversationException;
import com.diego.api.exception.PsidInvalidException;
import com.diego.api.model.User;
import com.diego.api.mapper.facebook.response.ToUserResponseDTO;
import com.diego.api.repositories.models.UserModel;
import com.diego.api.repositories.MessageRepository;
import com.diego.api.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "provider.option", havingValue = "f")
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
    public ArrayList<User> showUsers() {

        ResponseDTO response = facebookClient.getUsers(); //obtenemos los usuarios que facebook nos da
        ArrayList<User> users = ToUserResponseDTO.toUsersDTO(response);
        updateUsers(users);

        return users;
    }

    //enviar un mensaje
    @Override
    public void sendMessage(Object to, String message) {
        //Debe llamarse desde fuera
        UserModel user = (UserModel) to;
        String psid = user.getPsid();

        if (psid == null) {
            throw new PsidInvalidException();
        } else {
            facebookClient.sendMessage(psid, message);
        }
    }

    //ver conversacion de Messenger de un usuario
    public UserMessagesDTO showMessages(Integer phone) {

        Optional<UserModel> user = userRepository.findById(phone);
        UserModel userModel = user.get();

        String idConversacion = userModel.getIdConversacion();

        if (idConversacion == null) {
            throw new IdConversationException();
        }

        UserMessagesDTO response = facebookClient.seeUsersMessages(idConversacion);

        return response;
    }

    //verificamos el mensaje de un usuario cuando nos escribre (Webhook)
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

    //buscamos a un usuario en la base de datos por medio de su nickname
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

    //Actualizamos los id's de conversacion de usuarios REGISTRADOS en la base de datos
    public void updateUsers(ArrayList<User> users) {

        ArrayList<UserModel> usersModel = (ArrayList<UserModel>) userRepository.findAll();

        for (User user : users) {
            for (UserModel userModel : usersModel) {
                if (user.getPsid().equals(userModel.getPsid())) {
                    userModel.setIdConversacion(user.getIdConversacion());
                    userRepository.save(userModel);
                }
            }
        }
    }
}
