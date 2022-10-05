package com.diego.api.controllers;

import com.diego.api.dto.user.request.save_user.UserDTO;
import com.diego.api.dto.user.request.send_message.RequestMessageDTO;
import com.diego.api.dto.user.response.send_message.ResponseSendMessageDTO;
import com.diego.api.dto.user.response.show_users.UserToShowDTO;
import com.diego.api.service.UserService;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * El rol de esta clase es consumir todos los servicios segun el url y la
 * peticion HTTP esta clase se comunica con la clase UserService.
 *
 * @author HP
 */
@RestController
@RequestMapping(value = "/v1/users/")

public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*P E T I C I ON E S 
                                                                G E T
     */
    @CrossOrigin(origins="http://localhost:3000/")
    @GetMapping("/see")
    public ArrayList<UserToShowDTO> getUsers() {
        logger.info("Receiving request GET to /v1/users/");
        return userService.getUsers();
    }

    //peticiones                                            POST
    @PostMapping("/save")
    public void saveUser(@RequestBody UserDTO user) {
        logger.info("Receiving request POST to /v1/users/");
        userService.saveUser(user);
    }

    // Peticion para enviar un mensaje
    // Aquí, dependiendo de la configuración (app.properties) se enviará un mensaje al usuario,
    // ya sea por WhatsApp o por Messenger.
    @PostMapping("/messages/send")
    public ResponseSendMessageDTO enviarMensaje(@RequestBody RequestMessageDTO mensaje) {
        logger.info("Receiving request POST to /v1/users/messages/send");
        logger.info("*-*-*-ENTRANDO A /enviarMensaje*-*-*-");
        ResponseSendMessageDTO responseSend = userService.enviarMensaje(mensaje);
        return responseSend;
    }

}
