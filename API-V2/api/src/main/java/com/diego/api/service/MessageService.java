package com.diego.api.service;

import com.diego.api.dto.facebook_manager.webhook.responseMessenger.ResponseMessengerDTO;
import com.diego.api.models.MessageModel;
import com.diego.api.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    /**
     * public void saveMessage(MessageModel mensaje) {
        messageRepository.save(mensaje);
    }
    
    
    
    public MessageModel mapMessage(ResponseMessengerDTO message){
        
        Integer id = 0;
        String psid = message.getEntry()[0].getMessaging()[0].getSender().getId();
        String mensaje = message.getEntry()[0].getMessaging()[0].getMessage().getText();
        
        MessageModel messageModel = new MessageModel(id, psid, mensaje);
        
        return messageModel;
    }
     */

}
