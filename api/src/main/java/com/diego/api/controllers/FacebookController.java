package com.diego.api.controllers;

import com.diego.api.client.messages.facebook.model.response.in.messages_user.UserMessagesDTO;
import com.diego.api.controllers.client.facebook.request.notifyMessage.RequestMessengerDTO;
import com.diego.api.client.messages.facebook.model.response.out.show_user.UserResponseDTO;
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

    public FacebookController(FacebookService facebookService) {
        this.facebookService = facebookService;
    }

    @GetMapping("/users/messages/see")
    public UserMessagesDTO seeUserMessages(@RequestParam Integer userId) {
        logger.info("Receiving request to /users/messages/see");
        UserMessagesDTO response = facebookService.showMessages(userId);
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
        logger.info("Receiving request POST to /users/messages/notify: " + field);

        facebookService.verifyUser(field);

    }

}
