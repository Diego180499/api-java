/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.api.client.whatsapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author german.estacuy
 */
@Configuration
public class WhatsAppConfig {
    
    @Value("${token.whatsapp}")
    private String token;

    @Value("${url.baseurl}")
    private String baseURL;

    public String getToken() {
        return token;
    }

    public String getBaseURL() {
        return baseURL;
    }
       
}
