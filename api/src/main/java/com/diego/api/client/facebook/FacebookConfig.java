/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.api.client.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author german.estacuy
 */

@Configuration
public class FacebookConfig {
    
        /* URL'S */
    @Value("${url.enviar.mensaje}")
    private String urlSend;

    @Value("${token.page}")
    private String pageToken;

    @Value("${url.obtenerusuarios}")
    private String urlObtenerUsuarios;

    @Value("${url.obtenermensajes}")
    private String urlMensajesUsuarios;
    /*---*/

    public String getUrlSend() {
        return urlSend;
    }

    public String getPageToken() {
        return pageToken;
    }

    public String getUrlObtenerUsuarios() {
        return urlObtenerUsuarios;
    }

    public String getUrlMensajesUsuarios() {
        return urlMensajesUsuarios;
    }

    
    
}
