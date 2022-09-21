package com.diego.api.service;

import com.diego.api.configuration.ProviderConfig;
import com.diego.api.dto.user.request.save_user.UserDTO;
import com.diego.api.dto.user.request.send_message.RequestMessageDTO;
import com.diego.api.dto.user.response.show_users.UserToShowDTO;
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

    private ProviderConfig providerConfig;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    UserRepository userRepository;

    WhatsappService whatsAppService;
    FacebookService facebookService;

    public UserService(UserRepository userRepository, WhatsappService ws, FacebookService fb, ProviderConfig provider) {
        this.userRepository = userRepository;
        this.whatsAppService = ws;
        this.facebookService = fb;
        this.providerConfig = provider;
    }

    //          Métodos respecto a la base de datos
    /*Guardar un usuario*/
    public void saveUser(UserDTO user) {
        UserModel userModel = UserMap.toUserModel(user);
        userRepository.save(userModel);
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
    public void enviarMensaje(RequestMessageDTO mensaje) {
        logger.info("-*-*-*-*-*-*-ENTRANDO AL METODO PARA ENVIAR MENSAJE DE USER SERVICE*-*-*-*-*-*-");
        Integer to = mensaje.getId();
        String message = mensaje.getMensaje();

        UserModel usuario = findUser(to);

        if (providerConfig.getProvider().equals("w")) {
            logger.info("-*-*-*-*-*-*-ENTRANDO A LA CONDICION PARA ENVIAR MENSAJE DE USER SERVICE*-*-*-*-*-*-");
            whatsAppService.sendMessage(usuario, message);
        } else if (providerConfig.getProvider().equals("f")) {
            facebookService.sendMessage(usuario, message);
        }

    }
}
