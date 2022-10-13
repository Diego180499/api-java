
package com.diego.api.client.facebook.dto.response;

import com.diego.api.client.facebook.dto.response.DataDTO;

public class ResponseDTO {
    
   private DataDTO[] data;

    public DataDTO[] getData() {
        return data;
    }

    public void setData(DataDTO[] data) {
        this.data = data;
    }
   
   
}
