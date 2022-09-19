package com.diego.api.client;

import com.diego.api.dto.facebook_manager.ResponseDTO;
import com.diego.api.dto.facebook_manager.ResponseMessageDTO;
import com.diego.api.dto.facebook_manager.responseDTO.UserMessagesDTO;
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

    /* URL'S */
    @Value("${url.enviar.mensaje}")
    private String urlSend;

    @Value("${token.page}")
    private String PAGE_TOKEN;

    @Value("${url.obtenerusuarios}")
    private String urlObtenerUsuarios;

    @Value("${url.obtenermensajes}")
    private String urlMensajesUsuarios;
    /*---*/

    private RestTemplate restTemplate;

    public FacebookClient(RestTemplate restTemplate) {
        logger.info("*-*-*-*-*--**-CONSTRUCTOR DE FACEBOOK CLEINT*-*-*-*-*-*-");
        this.restTemplate = restTemplate;
    }

    /*Le solicitamos a Facebook que nos devuelva los usuarios que han escrito
    a nuestra página de facebook*/
    public ResponseDTO obtenerUsuarios() {
        String url = urlObtenerUsuarios;
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("field", "participants");
        queryParams.put("pageToken", PAGE_TOKEN);

        URI uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(queryParams).toUri();
        System.out.println("uri: " + uri.toString());

        //restTemplate = new RestTemplate();
        ResponseDTO response = restTemplate.getForObject(uri, ResponseDTO.class);
        return response;
    }

    public void enviarMensaje(String psid, String mensaje) {

        String url = urlSend;
        String psidValue = "{id:" + psid + "}";
        String messageValue = "{text:'" + mensaje + "'}";
        String messagingType = "RESPONSE";
        String accessToken = PAGE_TOKEN;

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

        String url = urlMensajesUsuarios;

        Map<String, String> params = new HashMap<String, String>();
        params.put("IdConversacion", idConversacion);
        params.put("field", fieldValue);
        params.put("pageToken", PAGE_TOKEN);

        URI uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(params).toUri();
        System.out.println("uri: " + uri.toString());

        //restTemplate = new RestTemplate();
        UserMessagesDTO response = restTemplate.getForObject(uri, UserMessagesDTO.class);
        return response;

    }

}
