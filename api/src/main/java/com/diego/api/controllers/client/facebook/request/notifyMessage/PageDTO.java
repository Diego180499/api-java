package com.diego.api.controllers.client.facebook.request.notifyMessage;

import java.util.ArrayList;

public class PageDTO {

    private String id;
    private ArrayList<MessageDTO> messaging;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<MessageDTO> getMessaging() {
        return messaging;
    }

    public void setMessaging(ArrayList<MessageDTO> messaging) {
        this.messaging = messaging;
    }

    

}
