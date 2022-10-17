package com.diego.api.controllers.whatsApp;

import com.diego.api.client.whatsapp.dto.request.MessageDefaultDTO;
import com.diego.api.controllers.whatsApp.dto.request.RequestWhatsappMessageDTO;
import com.diego.api.exception.InvalidWhatsAppRequestException;
import com.diego.api.service.WhatsappService;
import java.util.Map;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/whatsapp/")
@ConditionalOnProperty(value = "provider.option", havingValue = "w")
public class WhatsappController {

    Logger logger = LoggerFactory.getLogger(WhatsappController.class);

    private WhatsappService whatsappService;

    public WhatsappController(WhatsappService whatsappService) {
        this.whatsappService = whatsappService;
    }

    //peticion POST
    //enviamos un mensaje por default a un usuario
    @PostMapping("/sendDefault")
    public void mensajeInicial(@RequestBody MessageDefaultDTO body) {
        whatsappService.sendDefaultMessage(body);
    }

    // WEBHOOK
    @GetMapping("/messages")
    public String prueba(@RequestParam Map<String, String> respuesta) {
        logger.info("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- ENTRANDO A /whats mediante GET *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        return respuesta.get("hub.challenge");
    }

    //notificacion de cuando recibimos un mensaje por whatsapp
    @PostMapping("/messages")
    public void notifyWhatsapp(@RequestBody RequestWhatsappMessageDTO respuesta) {
        logger.info("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- ENTRANDO A /whats mediante POST *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        try {
            whatsappService.verifyUser(respuesta);
        } catch (InvalidWhatsAppRequestException ex) {
            java.util.logging.Logger.getLogger(WhatsappController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
