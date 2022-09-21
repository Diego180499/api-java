/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.api.client.messages.whatsapp.model.response.in.send_message;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ResponseSendMessageDTO {
    
    private ArrayList<ContactDTO> contacts;

    public ArrayList<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactDTO> contacts) {
        this.contacts = contacts;
    }
    
    
    
}
