package com.diego.api.controllers.whatsApp;

import com.diego.api.client.whatsapp.dto.request.MessageDefaultDTO;
import com.diego.api.controllers.whatsApp.dto.request.RequestWhatsappMessageDTO;
import com.diego.api.service.WhatsappService;
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
@RequestMapping("v1/whatsapp/")
public class WhatsappController {

    Logger logger = LoggerFactory.getLogger(WhatsappController.class);

    WhatsappService whatsappService;

    public WhatsappController(WhatsappService ws) {
        this.whatsappService = ws;
    }

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

    @PostMapping("/messages")
    //ResponseWhatsappMessageDTO
    public void notifyWhatsapp(@RequestBody RequestWhatsappMessageDTO respuesta) {
        //TODO: mandar a llamar el name del contacto de whatsapp y agregarlo a la base de datos.
        logger.info("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- ENTRANDO A /whats mediante POST *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        whatsappService.verifyUser(respuesta);
    }

}