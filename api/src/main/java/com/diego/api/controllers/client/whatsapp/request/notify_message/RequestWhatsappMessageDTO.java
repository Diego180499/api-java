package com.diego.api.controllers.client.whatsapp.request.notify_message;

import com.diego.api.controllers.client.whatsapp.request.notify_message.EntryDTO;
import java.util.ArrayList;

public class RequestWhatsappMessageDTO {
    
    private ArrayList<EntryDTO> entry;

    public ArrayList<EntryDTO> getEntry() {
        return entry;
    }

    public void setEntry(ArrayList<EntryDTO> entry) {
        this.entry = entry;
    }
    
    

}
