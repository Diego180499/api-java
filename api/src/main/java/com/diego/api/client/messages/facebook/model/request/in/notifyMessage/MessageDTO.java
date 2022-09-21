
package com.diego.api.client.messages.facebook.model.request.in.notifyMessage;

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
