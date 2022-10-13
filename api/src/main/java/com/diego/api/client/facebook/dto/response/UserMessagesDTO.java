/*
Clase para mapear lo que FACEBOOK me da como resultado de la peticion de
obtener los mensajes de un usuario
*/
package com.diego.api.client.facebook.dto.response;

public class UserMessagesDTO {
    
    private UserMessageDTO messages;

    public UserMessageDTO getMessages() {
        return messages;
    }

    public void setMessages(UserMessageDTO messages) {
        this.messages = messages;
    }
    
    
}
