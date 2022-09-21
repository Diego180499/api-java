
package com.diego.api.client.messages.whatsapp.model.request.in.notify_message;

import com.diego.api.client.messages.whatsapp.model.request.in.notify_message.MessageDTO;
import com.diego.api.client.messages.whatsapp.model.request.in.notify_message.ContactDTO;
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
