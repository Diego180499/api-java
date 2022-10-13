package com.diego.api.controllers.facebook;

import com.diego.api.controllers.facebook.dto.request.RequestMessengerDTO;
import com.diego.api.service.FacebookService;
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
