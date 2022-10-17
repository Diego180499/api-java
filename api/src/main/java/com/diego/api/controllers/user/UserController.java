package com.diego.api.controllers.user;

import com.diego.api.client.facebook.dto.response.UserMessagesDTO;
import com.diego.api.controllers.user.dto.request.UserDTO;
import com.diego.api.controllers.user.dto.request.RequestMessageDTO;
import com.diego.api.controllers.user.dto.response.UserToShowDTO;
import com.diego.api.exception.ApiJavaException;
import com.diego.api.exception.IdConversationException;
import com.diego.api.model.User;
import com.diego.api.service.FacebookService;
import com.diego.api.service.UserService;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    //constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //peticion GET 
    //obtener usuarios de la base de datos
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/see")
    public ResponseEntity getUsers() {
        logger.info("Receiving request GET to /v1/users/");
        ArrayList<UserToShowDTO> users = userService.getUsers();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(users);
    }

    //peticion POST
    //Guardar un usuario a la base de datos
    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/save")
    //UserDTO
    public ResponseEntity saveUser(@RequestBody UserDTO user) {
        logger.info("Receiving request POST to /v1/users/");
        try {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Creado");
        } catch (ApiJavaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    // Peticion para enviar un mensaje
    // Aquí, dependiendo de la configuración (app.properties) se enviará un mensaje al usuario,
    // ya sea por WhatsApp o por Messenger.
    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/messages/send")
    public ResponseEntity sendMessage(@RequestBody RequestMessageDTO mensaje) {
        try {
            logger.info("Receiving request POST to /v1/users/messages/send");
            logger.info("*-*-*-ENTRANDO A /enviarMensaje*-*-*-");
            userService.sendMessage(mensaje);
            return ResponseEntity.status(HttpStatus.OK).body("Mensaje Enviado Correctamente");//ResponseEntity.ok().build();
        } catch (ApiJavaException aje) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(aje.getMessage());
        }
    }
}
