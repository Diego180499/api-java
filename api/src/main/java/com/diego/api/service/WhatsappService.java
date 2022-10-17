package com.diego.api.service;

import com.diego.api.client.whatsapp.WhatsAppClient;
import com.diego.api.client.whatsapp.dto.request.MessageDefaultDTO;
import com.diego.api.client.whatsapp.dto.request.PersonalizedMessageDTO;
import com.diego.api.controllers.whatsApp.dto.request.RequestWhatsappMessageDTO;
import com.diego.api.exception.ApiJavaException;
import com.diego.api.exception.InvalidWhatsAppRequestException;
import com.diego.api.exception.WhatsAppException;
import com.diego.api.repositories.UserRepository;
import com.diego.api.repositories.models.UserModel;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "provider.option", havingValue = "w")
public class WhatsappService implements MessageService {

    Logger logger = LoggerFactory.getLogger(WhatsappService.class);

    WhatsAppClient whatsappClient;
    UserRepository userRepository;

    public WhatsappService(WhatsAppClient whatsappClient, UserRepository userRepository) {

        this.whatsappClient = whatsappClient;
        this.userRepository = userRepository;
    }

    public void sendDefaultMessage(MessageDefaultDTO request) {

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
            logger.info("*-*-*-*-*-*-*-*-*-Usuario no tiene id de whatsapp");
            mensajeWhatsapp.setNumero(usuario.getExtension() + "" + usuario.getTelefono());
        } else {
            mensajeWhatsapp.setNumero(usuario.getId_whatsapp());
        }

        mensajeWhatsapp.setMensaje(message);

        try {
            whatsappClient.sendMessage(mensajeWhatsapp);
        } catch (InvalidWhatsAppRequestException ex) {
            throw new ApiJavaException("El numero de celular no es valido");
        } catch (WhatsAppException we) {
            throw new ApiJavaException(we.getMessage()
            );
        }

    }

    public void verifyUser(RequestWhatsappMessageDTO respuesta) throws InvalidWhatsAppRequestException {
        try {
            logger.info("*-*-*-*-*-*-*-*-*-Entrando a verifyUser");
            String phone = respuesta.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getFrom();
            String nickName = respuesta.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getText().getBody();
            PersonalizedMessageDTO mensajeWhatsapp = new PersonalizedMessageDTO();

            UserModel user = findUserWithNickName(nickName);

            if (user == null) {
                logger.info("*-*-*-*-*-*-*-*-*-Usuario no encontrado");
                mensajeWhatsapp.setNumero(phone);
                mensajeWhatsapp.setMensaje("Para poder chatear, escribe tu NickName que ingresaste en tu registro. Si no tienes uno, registrate en la Pagina Oficial.");
            } else {
                logger.info("*-*-*-*-*-*-*-*-*-Usuario encontrado");
                mensajeWhatsapp.setNumero(phone);
                mensajeWhatsapp.setMensaje("Hola " + user.getNombre() + ", es un gusto saludarte");
                updateUser(phone, user);
            }

            whatsappClient.sendMessage(mensajeWhatsapp);
        } catch (NullPointerException e) {
            throw new ApiJavaException("El numero es invalido");
        }

    }

    public UserModel findUserWithNickName(String nickName) {

        ArrayList<UserModel> usersModel = (ArrayList<UserModel>) userRepository.findAll();

        for (UserModel userModel : usersModel) {
            String nickNameUser = userModel.getNickName();
            if (nickNameUser.equals(nickName)) {
                return userModel;
            }
        }

        return null;
    }

    public void updateUser(String id_whatsapp, UserModel userModel) {

        userModel.setId_whatsapp(id_whatsapp);
        userRepository.save(userModel);

    }

}
