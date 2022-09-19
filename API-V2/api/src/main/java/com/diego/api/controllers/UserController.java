package com.diego.api.controllers;

import com.diego.api.dto.user_manager.user_message_dto.RequestMessageDTO;
import com.diego.api.models.UsuarioModel;
import com.diego.api.service.MessageService;
import com.diego.api.service.UserService;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * El rol de esta clase es consumir todos los servicios segun el url y la
 * peticion HTTP esta clase se comunica con la clase UserService.
 *
 * @author HP
 */
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    UserService userService;

    MessageService messageService;

    public UserController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    /*P E T I C I ON E S 
                                                                G E T
     */
    @GetMapping()
    public ArrayList<UsuarioModel> getUsers() {
        return userService.getUsers();
    }

    //peticiones                                            POST
    @PostMapping()
    public UsuarioModel saveUser(@RequestBody UsuarioModel user) {
        return userService.saveUser(user);
    }

    // Peticion para enviar un mensaje
    // Aquí, dependiendo de la configuración (app.properties) se enviará un mensaje al usuario,
    // ya sea por WhatsApp o por Messenger.
    @PostMapping("/enviarMensaje")
    public void enviarMensaje(@RequestBody RequestMessageDTO mensaje) {
        logger.info("*-*-*-ENTRANDO A /enviarMensaje");
        userService.enviarMensaje(mensaje);
    }

}
