/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.api.client.messages.whatsapp.model.request.in.notify_message;

import com.diego.api.client.messages.whatsapp.model.request.in.notify_message.ChangeDTO;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class EntryDTO {
    
    private ArrayList<ChangeDTO> changes;

    public ArrayList<ChangeDTO> getChanges() {
        return changes;
    }

    public void setChanges(ArrayList<ChangeDTO> changes) {
        this.changes = changes;
    }
    
    
    
}