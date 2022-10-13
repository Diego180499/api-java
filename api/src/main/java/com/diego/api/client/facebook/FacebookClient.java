package com.diego.api.client.facebook;

import com.diego.api.client.facebook.dto.response.ResponseDTO;
import com.diego.api.client.facebook.dto.response.UserMessagesDTO;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Esta clase es la encargada de comunicarse con Facebook.
 *
 * @author HP
 */
@Component
public class FacebookClient {

    Logger logger = LoggerFactory.getLogger(FacebookClient.class);

    private RestTemplate restTemplate;

    private FacebookConfig facebookConfig;

    public FacebookClient(RestTemplate restTemplate, FacebookConfig facebookConfig) {
        logger.info("*-*-*-*-*--**-CONSTRUCTOR DE FACEBOOK CLEINT*-*-*-*-*-*-");
        this.restTemplate = restTemplate;
        this.facebookConfig = facebookConfig;
    }

    /*Le solicitamos a Facebook que nos devuelva los usuarios que han escrito
    a nuestra página de facebook*/
    public ResponseDTO getUsers() {
        String url = facebookConfig.getUrlObtenerUsuarios();
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("field", "participants");
        queryParams.put("pageToken", facebookConfig.getPageToken());

        URI uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(queryParams).toUri();
        System.out.println("uri: " + uri.toString());

        ResponseDTO response = restTemplate.getForObject(uri, ResponseDTO.class);
        return response;
    }

    public Integer sendMessage(String psid, String mensaje) {

        String url = facebookConfig.getUrlSend();
        String psidValue = "{id:" + psid + "}";
        String messageValue = "{text:'" + mensaje + "'}";
        String messagingType = "RESPONSE";
        String accessToken = facebookConfig.getPageToken();

        Map<String, String> params = new HashMap<String, String>();
        params.put("psidValue", psidValue);
        params.put("messageValue", messageValue);
        params.put("messagingType", messagingType);
        params.put("accessToken", accessToken);

        URI uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(params).toUri();
        System.out.println("uri: " + uri.toString());

        //restTemplate = new RestTemplate();
        // ResponseMessageDTO.class
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, HttpEntity.EMPTY, String.class);
        String respuesta = String.valueOf(response);
        logger.info("****************************************Response from Facebook of send message---> : " + respuesta);
        return response.getStatusCode().value();
    }

    public UserMessagesDTO verMensajesUsuario(String idConversacion) {

        String fieldValue = "messages{message}";

        String url = facebookConfig.getUrlMensajesUsuarios();

        Map<String, String> params = new HashMap<String, String>();
        params.put("IdConversacion", idConversacion);
        params.put("field", fieldValue);
        params.put("pageToken", facebookConfig.getPageToken());

        URI uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(params).toUri();
        System.out.println("uri: " + uri.toString());

        UserMessagesDTO response = restTemplate.getForObject(uri, UserMessagesDTO.class);
        return response;

    }

}
