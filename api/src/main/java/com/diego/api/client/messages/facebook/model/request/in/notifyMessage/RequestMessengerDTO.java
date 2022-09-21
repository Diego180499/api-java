package com.diego.api.client.messages.facebook.model.request.in.notifyMessage;

public class RequestMessengerDTO {

    private PageDTO[] entry;

    public PageDTO[] getEntry() {
        return entry;
    }

    public void setEntry(PageDTO[] entry) {
        this.entry = entry;
    }

}
