package com.diego.api.service;

import com.diego.api.configuration.ProviderConfig;
import com.diego.api.controllers.user.dto.request.UserDTO;
import com.diego.api.controllers.user.dto.request.RequestMessageDTO;
import com.diego.api.controllers.user.dto.response.UserToShowDTO;
import com.diego.api.mapper.user.UserMap;
import com.diego.api.repositories.models.UserModel;
import com.diego.api.repositories.UserRepository;
import java.util.ArrayList;
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

    UserRepository userRepository;
    
    MessageService messageService;


    public UserService(UserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
    }

    //          Métodos respecto a la base de datos
    /*Guardar un usuario*/
    public Integer saveUser(UserDTO user) {
        UserModel userModel = UserMap.toUserModel(user);

        if (userModel.getNickName() == null || userModel.getExtension() == null || userModel.getTelefono() == null) {
            return 400;
        }
        userRepository.save(userModel);
        return 200;
    }

    /*Buscar un usuario por ID*/
    public UserModel findUser(Integer id) {
        Optional<UserModel> usuario = userRepository.findById(id);
        UserModel usuarioModel = usuario.get();
        return usuarioModel;
    }

    /*Obtener todos los usuarios de la base de datos*/
    public ArrayList<UserToShowDTO> getUsers() {

        ArrayList<UserModel> users = (ArrayList<UserModel>) userRepository.findAll();
        ArrayList<UserToShowDTO> usersShow = UserMap.toUsersShow(users);

        return usersShow;
    }

    /*agregar un usuario a la base de datos*/
    public Boolean exist(UserModel usuario) {
        ArrayList<UserModel> usuarios = (ArrayList<UserModel>) userRepository.findAll();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getPsid().equals(usuario.getPsid())) {
                return true;
            }
        }
        return false;
    }

    public void agregarUsuario(UserModel usuario) {
        if (!exist(usuario)) {
            userRepository.save(usuario);
        }
    }

    // enviar mensajes
    public void sendMessage(RequestMessageDTO mensaje) {
        logger.info("-*-*-*-*-*-*-ENTRANDO AL METODO PARA ENVIAR MENSAJE DE USER SERVICE*-*-*-*-*-*-");
        //ResponseSendMessageDTO responseSend = new ResponseSendMessageDTO();
        Integer to = mensaje.getId();
        String message = mensaje.getMensaje();
        UserModel usuario = findUser(to);
        //responseSend.setMensaje("Mensaje enviado correctamente");
        messageService.sendMessage(usuario, message);
    }
}
