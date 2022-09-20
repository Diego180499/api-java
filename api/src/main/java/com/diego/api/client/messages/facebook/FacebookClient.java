package com.diego.api.client.messages.facebook;

import com.diego.api.client.messages.facebook.model.ResponseDTO;
import com.diego.api.client.messages.facebook.model.ResponseMessageDTO;
import com.diego.api.client.messages.facebook.model.response.UserMessagesDTO;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
        this.facebookConfig=facebookConfig;
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

        //restTemplate = new RestTemplate();
        ResponseDTO response = restTemplate.getForObject(uri, ResponseDTO.class);
        return response;
    }

    public void sendMessage(String psid, String mensaje) {

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
        restTemplate.exchange(uri, HttpMethod.POST, HttpEntity.EMPTY, ResponseMessageDTO.class);

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

        //restTemplate = new RestTemplate();
        UserMessagesDTO response = restTemplate.getForObject(uri, UserMessagesDTO.class);
        return response;

    }

}
