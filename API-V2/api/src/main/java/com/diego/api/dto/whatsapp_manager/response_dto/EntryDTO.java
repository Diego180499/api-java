/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.api.dto.whatsapp_manager.response_dto;

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
