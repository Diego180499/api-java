package com.diego.api.dto.whatsapp_manager.message_default.response;

import java.util.ArrayList;

public class ResponseDTO {
    
    private String messaging_product;
    private ArrayList<ContactDTO> contacts;
    private ArrayList<MessageDTO> messages;

    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public ArrayList<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactDTO> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessageDTO> messages) {
        this.messages = messages;
    }
    
    
    
    
}
