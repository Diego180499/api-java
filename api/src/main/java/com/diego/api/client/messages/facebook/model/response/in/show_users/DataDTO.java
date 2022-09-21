
package com.diego.api.client.messages.facebook.model.response.in.show_users;


public class DataDTO {
    
     private ParticipantsDTO participants;
    private String id;

    public ParticipantsDTO getParticipants() {
        return participants;
    }

    public void setParticipants(ParticipantsDTO participants) {
        this.participants = participants;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
}
