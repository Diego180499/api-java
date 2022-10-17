package com.diego.api.client.whatsapp;

import com.diego.api.client.whatsapp.dto.request.MessageDefaultDTO;
import com.diego.api.client.whatsapp.dto.request.PersonalizedMessageDTO;
import com.diego.api.client.whatsapp.dto.response.ResponseSendMessageDTO;
import com.diego.api.exception.InvalidWhatsAppRequestException;
import com.diego.api.exception.WhatsAppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class WhatsAppClient {

    Logger logger = LoggerFactory.getLogger(WhatsAppClient.class);

    private RestTemplate restTemplate;
    private WhatsAppConfig whatsAppConfig;

    public WhatsAppClient(RestTemplate restTemplate, WhatsAppConfig whatsAppConfig) {
        this.restTemplate = restTemplate;
        this.whatsAppConfig = whatsAppConfig;
    }
    
    //solicitamos enviarle un mensaje por default a un usuario
    public void sendDefaultMessage(MessageDefaultDTO request) {
        String url = whatsAppConfig.getBaseURL();
        String token = whatsAppConfig.getToken();
        logger.info("++++++++++++++++++++++El token de whatsapp es: " + token);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        ResponseEntity<ResponseSendMessageDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, ResponseSendMessageDTO.class);
        logger.info("Le enviaste un mensaje a : " + response.getBody().getContacts().get(0).getInput());
    }
    
    
    //solicitamos enviarle un mensaje a un usuario
    public ResponseSendMessageDTO sendMessage(PersonalizedMessageDTO mensaje) throws InvalidWhatsAppRequestException {
       
        String url = whatsAppConfig.getBaseURL();
        String token = whatsAppConfig.getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(mensaje.toString(), headers);
       try {
           ResponseEntity<ResponseSendMessageDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, ResponseSendMessageDTO.class);
           logger.info("message sent to: {}", response.getBody().getContacts().get(0).getInput());
           return response.getBody();
        } catch (HttpClientErrorException e) {
            logger.error("*-->Fallo Whatsapp-->",e);
            
            if(e.getStatusCode()==HttpStatus.BAD_REQUEST){
                throw new InvalidWhatsAppRequestException();
            }
            throw new WhatsAppException("Error en conexion con WhatsApp");
        }
        
    }
}
