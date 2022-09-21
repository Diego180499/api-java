package com.diego.api.client.messages.facebook.model.request.in.notifyMessage;

public class PageDTO {

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
