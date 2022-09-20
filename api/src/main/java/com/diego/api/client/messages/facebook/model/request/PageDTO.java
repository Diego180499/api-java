/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.api.client.messages.facebook.model.request;

/**
 *
 * @author HP
 */
public class PageDTO {
    
    /*
    id
    messaging [] arreglo de los datos del mensaje
    */
    
    private String id;
    private MessageDTO[] messaging;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MessageDTO[] getMessaging() {
        return messaging;
    }

    public void setMessaging(MessageDTO[] messaging) {
        this.messaging = messaging;
    }
    
    
    
}
