package com.diego.api.controllers.whatsApp.dto.request;

import com.diego.api.controllers.whatsApp.dto.request.EntryDTO;
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
