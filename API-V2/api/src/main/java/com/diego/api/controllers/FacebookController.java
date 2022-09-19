package com.diego.api.controllers;

import com.diego.api.dto.facebook_manager.MessageDTO;
import com.diego.api.dto.facebook_manager.ResponseDTO;
import com.diego.api.dto.facebook_manager.responseDTO.UserMessagesDTO;
import com.diego.api.dto.facebook_manager.webhook.responseMessenger.ResponseMessengerDTO;
import com.diego.api.models.MessageModel;
import com.diego.api.models.UsuarioModel;
import com.diego.api.service.FacebookService;
import com.diego.api.service.MessageService;
import com.diego.api.service.UserService;
import java.util.ArrayList;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookController {

    Logger logger = LoggerFactory.getLogger(FacebookController.class);

    FacebookService facebookService;
    UserService userService;
    MessageService messageService;

    public FacebookController(FacebookService facebookService, MessageService messageService, UserService userService) {
        logger.info("*****************************FACEBOOK CONTROLLER CREADO**************");
        this.facebookService = facebookService;
        this.messageService = messageService;
        this.userService = userService;
    }

    /*
    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageDTO mensaje) {
        //logger.info("Receiving request to /send");
        //facebookService.enviarMensaje(mensaje);
    }
    */
    

    /**
     * Ver los usuarios que han escrito a la página
     *
     * @return
     */
    @GetMapping("/seeUsers")
    public ArrayList<UsuarioModel> allUsers() {
        logger.info("Receiving request to /seeUsers");
        ResponseDTO response2 = facebookService.obtenerUsuarios();
        ArrayList<UsuarioModel> usuarios = userService.agregarUsuarios(response2);
        return usuarios;
    }

    @GetMapping("/seeUserMessages")
    public UserMessagesDTO seeUserMessages(@RequestParam Integer userId) {
        UsuarioModel usuario = userService.findUser(userId);
        UserMessagesDTO response = facebookService.verMensajes(usuario);

        return response;
    }

    // WEBHOOK
    @GetMapping("/mensaje")
    public String webGet(@RequestParam Map<String, String> allParams) {

        logger.info("--*-**-*-*-*-*-*--**-*-*-*--*--*-*-*-*-*-*-*-*-*-*--*-*-*-Peticion recibida via GET *----->" + allParams);

        return allParams.get("hub.challenge");
    }

    @PostMapping("/mensaje")
    public void webPost(@RequestBody ResponseMessengerDTO field) {
        logger.info("--*-**-*-*-*-*-*--**-*-*-*--*--*-*-*-*-*-*-*-*-*-*--*-*-*-Peticion recibida via POST *----->" + field);
        logger.info("*-*-*-*-*-*-*-Entrando a mi webhook...*-*-*-*-*-*-*-*-");
        MessageModel mensaje = facebookService.mapMessage(field);
        facebookService.saveMessage(mensaje);

    }

}
