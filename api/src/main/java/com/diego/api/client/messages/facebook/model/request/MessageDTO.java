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
public class MessageDTO {
    
    /*
    sender  -> objeto que contiene el psid del usuario que envio el mensaje
    message -> datos del mensaje, entre ellos el texto del mensaje
    */
    
    private UserDTO sender;
    private DataMessageDTO message;

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public DataMessageDTO getMessage() {
        return message;
    }

    public void setMessage(DataMessageDTO message) {
        this.message = message;
    }

    
    
    
    
}
