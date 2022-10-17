package com.diego.api.service;

import com.diego.api.controllers.user.dto.request.UserDTO;
import com.diego.api.controllers.user.dto.request.RequestMessageDTO;
import com.diego.api.controllers.user.dto.response.UserToShowDTO;
import com.diego.api.exception.ApiJavaException;
import com.diego.api.exception.PsidInvalidException;
import com.diego.api.mapper.user.UserMap;
import com.diego.api.repositories.models.UserModel;
import com.diego.api.repositories.UserRepository;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * El rol de esta clase es implementar la lógica de negocio relacionadas con la
 * base de datos como CREATE, READ (CONSULTAR) , UPDATE, DELETE ... es decir el
 * CRUD y esta clase se comunica con la clase UserRepository.
 *
 * @author HP
 */
@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private MessageService messageService;

    public UserService(UserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }
    
    //Guardar un usuario
    public void saveUser(UserDTO user) {
        UserModel userModel = UserMap.toUserModel(user);
        agregarUsuario(userModel);
    }

    //Buscar un usuario por Telefono
    public UserModel findUser(Integer telefono) {
        Optional<UserModel> usuario = userRepository.findById(telefono);
        UserModel usuarioModel = usuario.get();
        return usuarioModel;
    }

    //Obtener todos los usuarios de la base de datos
    public ArrayList<UserToShowDTO> getUsers() {

        ArrayList<UserModel> users = (ArrayList<UserModel>) userRepository.findAll();
        ArrayList<UserToShowDTO> usersShow = UserMap.toUsersShow(users);

        return usersShow;
    }

    //verifica si un usuario existe en la base de datos
    public Boolean exist(UserModel usuario) {
        try {
            UserModel user = userRepository.findById(usuario.getTelefono()).get();
            return true;
        } catch (NoSuchElementException e) { //significa que no ha encontrado nada, por lo que no existe el usuario
            logger.error("Error ->", e);
            return false;
        }

    }
    
    //agregar un usuario a la base de datos
    public void agregarUsuario(UserModel usuario) {
        if (!exist(usuario)) {
            userRepository.save(usuario);
        } else {
            throw new ApiJavaException("El usuario con el numero: " + usuario.getTelefono() + ", ya ha sido registrado");
        }
    }

    // enviar mensajes
    public void sendMessage(RequestMessageDTO mensaje) {
        logger.info("-*-*-*-*-*-*-ENTRANDO AL METODO PARA ENVIAR MENSAJE DE USER SERVICE*-*-*-*-*-*-");
        //ResponseSendMessageDTO responseSend = new ResponseSendMessageDTO();
        Integer to = mensaje.getTelefono();
        String message = mensaje.getMensaje();
        UserModel usuario = findUser(to);
        //responseSend.setMensaje("Mensaje enviado correctamente");
        try {
            messageService.sendMessage(usuario, message);
        } catch (PsidInvalidException pe) {
            throw new ApiJavaException("El usuario no cuenta con PSID de Facebook");
        }
    }
}
