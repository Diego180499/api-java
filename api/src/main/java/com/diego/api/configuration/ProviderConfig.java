/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author german.estacuy
 */
@Configuration
public class ProviderConfig {
    
    @Value("${provider.option}")
    private String provider;

    public String getProvider() {
        return provider;
    }
    
}
