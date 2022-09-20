package com.diego.api.client.messages.whatsapp.model.response_dto;

import java.util.ArrayList;

public class ResponseWhatsappMessageDTO {
    
    private ArrayList<EntryDTO> entry;

    public ArrayList<EntryDTO> getEntry() {
        return entry;
    }

    public void setEntry(ArrayList<EntryDTO> entry) {
        this.entry = entry;
    }
    
    

}
