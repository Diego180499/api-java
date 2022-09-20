/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.api.client.messages.whatsapp.model.response_dto;

/**
 *
 * @author HP
 */
public class MessageDTO {
    
    private String from;
    private TextDTO text;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public TextDTO getText() {
        return text;
    }

    public void setText(TextDTO text) {
        this.text = text;
    }
    
    
}
