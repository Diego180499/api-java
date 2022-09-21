
package com.diego.api.client.messages.facebook.model.response.in.show_users;

import com.diego.api.client.messages.facebook.model.response.in.show_users.DataDTO;

public class ResponseDTO {
    
   private DataDTO[] data;

    public DataDTO[] getData() {
        return data;
    }

    public void setData(DataDTO[] data) {
        this.data = data;
    }
   
   
}
