package com.diego.api.controllers.user;

import com.diego.api.controllers.user.dto.request.UserDTO;
import com.diego.api.controllers.user.dto.request.RequestMessageDTO;
import com.diego.api.controllers.user.dto.response.ErrorMessageDTO;
import com.diego.api.controllers.user.dto.response.UserToShowDTO;
import com.diego.api.exception.ApiJavaException;
import com.diego.api.service.UserService;
import com.diego.api.utilities.UtilitieUser;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

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
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/see")
    public ArrayList<UserToShowDTO> getUsers() {
        logger.info("Receiving request GET to /v1/users/");
        return userService.getUsers();
    }

    //peticiones                    POST
    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/save")
    //UserDTO
    public ErrorMessageDTO saveUser(@RequestBody UserDTO user) {
        logger.info("Receiving request POST to /v1/users/");
        int codigo = userService.saveUser(user);

        return UtilitieUser.verifyCodeSaveUser(codigo);
    }

    // Peticion para enviar un mensaje
    // Aquí, dependiendo de la configuración (app.properties) se enviará un mensaje al usuario,
    // ya sea por WhatsApp o por Messenger.
    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/messages/send")
    public ResponseEntity sendMessage(@RequestBody RequestMessageDTO mensaje) {
       try{
            logger.info("Receiving request POST to /v1/users/messages/send");
            logger.info("*-*-*-ENTRANDO A /enviarMensaje*-*-*-");
            userService.sendMessage(mensaje);
            return ResponseEntity.ok().build();
       }catch(ApiJavaException aje){
          return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(aje.getMessage());
       }
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("{userId}/messages")
    public void getMessages(@RequestBody RequestMessageDTO mensaje) {
//TODO metodo para ver mensajes de un usuario
    }

    
    
    
    //EAAInUz9K7mcBAAteAewD0FZCcITVsleZApdTHBSohCHyH4M73eRQop0jnurjUFhW9bY4YlIKg5EqTKiDipuqcqHG7sOd4HrZBs3gg6NBKhGpksWkgwCmaJS7ZCWs4QIBMmf8PMMLkZAZAXZBaibBTHw9OocVmzWa03kaTK5WWxwZAF9Dohq1rVnMISEyioYxw8WAstIWoc5M5gZDZD
}
