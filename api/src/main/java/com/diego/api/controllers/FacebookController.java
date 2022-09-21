package com.diego.api.controllers;

import com.diego.api.client.messages.facebook.model.response.in.messages_user.UserMessagesDTO;
import com.diego.api.client.messages.facebook.model.request.in.notifyMessage.RequestMessengerDTO;
import com.diego.api.client.messages.facebook.model.response.out.show_user.UserResponseDTO;
import com.diego.api.repositories.models.MessageModel;
import com.diego.api.repositories.models.UserModel;
import com.diego.api.service.FacebookService;
import com.diego.api.service.UserService;
import java.util.ArrayList;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/facebook/")
public class FacebookController {

    Logger logger = LoggerFactory.getLogger(FacebookController.class);

    private FacebookService facebookService;
    private UserService userService;

    public FacebookController(FacebookService facebookService, UserService userService) {
        this.facebookService = facebookService;
        this.userService = userService;
    }

    /**
     * Ver los usuarios que han escrito a la página
     *
     * @return
     */
    @GetMapping("/users/see")
    public ArrayList<UserResponseDTO> allUsersFacebook() {
        logger.info("Receiving request to /users/see");
        ArrayList<UserResponseDTO> response = facebookService.showUsers();
        return response;
    }

    @GetMapping("/users/messages/see")
    public UserMessagesDTO seeUserMessages(@RequestParam Integer userId) {
        logger.info("Receiving request to /users/messages/see");
        UserModel userModel = userService.findUser(userId);
        UserMessagesDTO response = facebookService.showMessages(userModel);
        return response;
    }

    // WEBHOOK
    @GetMapping("/users/messages/notify")
    public String webGet(@RequestParam Map<String, String> allParams) {
        logger.info("Receiving request GET to /users/messages/notify");
        return allParams.get("hub.challenge");
    }

    @PostMapping("/users/messages/notify")
    public void webPost(@RequestBody RequestMessengerDTO field) {
        logger.info("Receiving request POST to /users/messages/notify");
        MessageModel mensaje = facebookService.mapMessage(field);
        facebookService.saveMessage(mensaje);
    }

}
