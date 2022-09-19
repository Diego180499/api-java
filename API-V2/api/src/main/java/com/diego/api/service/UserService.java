package com.diego.api.service;

import com.diego.api.dto.facebook_manager.MessageDTO;
import com.diego.api.dto.facebook_manager.ResponseDTO;
import com.diego.api.dto.facebook_manager.UserDTO;
import com.diego.api.dto.user_manager.user_message_dto.RequestMessageDTO;
import com.diego.api.dto.whatsapp_manager.personalized_message.PersonalizedMessageDTO;
import com.diego.api.models.UsuarioModel;
import com.diego.api.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${opcion.envio}")
    private String opcion;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    UserRepository userRepository;

    WhatsappService ws;
    FacebookService fb;
    
    public UserService(UserRepository userRepository, WhatsappService ws, FacebookService fb) {
        this.userRepository = userRepository;
        this.ws = ws;
        this.fb = fb;
    }

    public ArrayList<UsuarioModel> agregarUsuarios(ResponseDTO response) {
        ArrayList<UsuarioModel> usuarios = new ArrayList<>();

        for (int i = 0; i < response.getData().length; i++) {
            UserDTO usuario = response.getData()[i].getParticipants().getData().get(0);
            String idConversacion = response.getData()[i].getId();
            UsuarioModel usuarioModel = new UsuarioModel(usuario.getName(), usuario.getEmail(), usuario.getId(), 0, idConversacion);
            agregarUsuario(usuarioModel);
            usuarios.add(usuarioModel);
        }

        ArrayList<UsuarioModel> usuariosDB = getUsers();

        return usuariosDB;
    }

    //          Métodos respecto a la base de datos
    /*Guardar un usuario*/
    public UsuarioModel saveUser(UsuarioModel user) {
        return userRepository.save(user);
    }

    /*Buscar un usuario por ID*/
    public UsuarioModel findUser(Integer id) {
        Optional<UsuarioModel> usuario = userRepository.findById(id);
        UsuarioModel usuarioModel = usuario.get();
        return usuarioModel;
    }

    /*Obtener todos los usuarios de la base de datos*/
    public ArrayList<UsuarioModel> getUsers() {
        return (ArrayList<UsuarioModel>) userRepository.findAll();
    }

    /*agregar un usuario a la base de datos*/
    public Boolean exist(UsuarioModel usuario) {
        ArrayList<UsuarioModel> usuarios = getUsers();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getPsid().equals(usuario.getPsid())) {
                return true;
            }
        }
        return false;
    }

    public void agregarUsuario(UsuarioModel usuario) {
        if (!exist(usuario)) {
            userRepository.save(usuario);
        }
    }

    // enviar mensajes
    public void enviarMensaje(RequestMessageDTO mensaje) {
        logger.info("-*-*-*-*-*-*-ENTRANDO AL METODO PARA ENVIAR MENSAJE DE USER SERVICE*-*-*-*-*-*-");
        PersonalizedMessageDTO mensajeWhatsapp = new PersonalizedMessageDTO();
        MessageDTO mensajeFacebook = new MessageDTO();

        UsuarioModel usuario = findUser(mensaje.getId());

        if (opcion.equals("w")) {
            logger.info("-*-*-*-*-*-*-ENTRANDO A LA CONDICION PARA ENVIAR MENSAJE DE USER SERVICE*-*-*-*-*-*-");
            mensajeWhatsapp.setNumero("502" + usuario.getTelefono());
            mensajeWhatsapp.setMensaje(mensaje.getMensaje());
            ws.sendMessage(mensajeWhatsapp);
        } else if (opcion.equals("f")) {
            mensajeFacebook.setMensaje(mensaje.getMensaje());
            mensajeFacebook.setUsuario(mensaje.getId());
            fb.enviarMensaje(mensajeFacebook, usuario);
        }

    }
}
