
package com.diego.api.controllers.whatsApp.dto.request;

import com.diego.api.controllers.whatsApp.dto.request.MessageDTO;
import com.diego.api.controllers.whatsApp.dto.request.ContactDTO;
import java.util.ArrayList;

public class ValueDTO {
    
    private ArrayList<MessageDTO> messages;
    private ArrayList<ContactDTO> contacts;

    public ArrayList<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessageDTO> messages) {
        this.messages = messages;
    }

    public ArrayList<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactDTO> contacts) {
        this.contacts = contacts;
    }
    
    
    
    
    
}
