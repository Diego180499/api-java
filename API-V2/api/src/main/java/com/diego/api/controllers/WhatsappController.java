package com.diego.api.controllers;

import com.diego.api.dto.whatsapp_manager.message_default.RequestDTO;
import com.diego.api.dto.whatsapp_manager.response_dto.ResponseWhatsappMessageDTO;
import com.diego.api.service.WhatsappService;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhatsappController {

    Logger logger = LoggerFactory.getLogger(WhatsappController.class);

    WhatsappService ws;

    public WhatsappController(WhatsappService ws) {
        this.ws = ws;
    }

    @PostMapping("/sendDefault")
    public void mensajeInicial(@RequestBody RequestDTO body) {
        ws.sendDefaultMessage(body);
    }

   /*
     @PostMapping("/sendPM")
    public void mensaje(@RequestBody PersonalizedMessageDTO body) {
        ws.sendMessage(body);
    }
    */

    // WEBHOOK
    @GetMapping("/whats")
    public String prueba(@RequestParam Map<String, String> respuesta) {
        logger.info("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- ENTRANDO A /whats mediante GET *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        return respuesta.get("hub.challenge");
    }

    @PostMapping("/whats")
    public void pruebaP(@RequestBody ResponseWhatsappMessageDTO respuesta) {
        logger.info("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- ENTRANDO A /whats mediante POST *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        ResponseWhatsappMessageDTO mensaje = ws.mapRequest(respuesta);
        logger.info("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- ENTRANDO A /whats mediante POST: " + mensaje.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getText().getBody());
    }

}
