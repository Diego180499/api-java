package com.diego.api.controllers.facebook;

import com.diego.api.client.facebook.dto.response.UserMessagesDTO;
import com.diego.api.controllers.facebook.dto.request.RequestMessengerDTO;
import com.diego.api.exception.IdConversationException;
import com.diego.api.model.User;
import com.diego.api.service.FacebookService;
import java.util.ArrayList;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/facebook/")
@ConditionalOnProperty(value = "provider.option", havingValue = "f")
public class FacebookController {

    Logger logger = LoggerFactory.getLogger(FacebookController.class);

    private FacebookService facebookService;

    //constructor
    public FacebookController(FacebookService facebookService) {
        this.facebookService = facebookService;
    }

    //Ver los usuarios que nos han escrito a la página de Facebook
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/users")
    public ResponseEntity getFacebookUsers() {
        logger.info("Peticion GET a /v1/users/");
        ArrayList<User> users = facebookService.showUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    //ver la conversacion de Messenger de un usuario en específioo
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/messages/user/")
    public ResponseEntity getMessages(@RequestParam Integer phone) {
        logger.info("*-*-Peticion get a /v1/users/idConversacion/messages\n phone-> " + phone);
        try {
            UserMessagesDTO response = facebookService.showMessages(phone);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (IdConversationException ice) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error, el usuario no cuenta con un ID de conversacion de Facebook");
        }
    }

    // WEBHOOK
    @GetMapping("/users/messages/notify")
    public String webGet(@RequestParam Map<String, String> allParams) {
        logger.info("Receiving request GET to /users/messages/notify");
        return allParams.get("hub.challenge");
    }
    
    //nos notifica cuando un usuario nos ha escrito por messenger
    @PostMapping("/users/messages/notify")
    public void webPost(@RequestBody RequestMessengerDTO field) {
        logger.info("Receiving request POST to /users/messages/notify: " + field);

        facebookService.verifyUser(field);

    }

}
