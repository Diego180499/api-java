package com.diego.api.mapper.facebook.response;

import com.diego.api.client.facebook.dto.response.ResponseDTO;
import com.diego.api.model.User;
import java.util.ArrayList;

public class ToUserResponseDTO {

    //Mapea datos hacia usuarios de Facebook (UserDTO)
    public static ArrayList<User> toUsersDTO(ResponseDTO response) {
        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < response.getData().length; i++) {
            String nombre = response.getData()[i].getParticipants().getData().get(0).getName();
            String psid = response.getData()[i].getParticipants().getData().get(0).getId();
            String email = response.getData()[i].getParticipants().getData().get(0).getEmail();
            String idConversacion = response.getData()[i].getId();
            User usuario = new User();
            usuario.setNombre(nombre);
            usuario.setPsid(psid);
            usuario.setIdConversacion(idConversacion);
            usuario.setCorreo(email);
            users.add(usuario);
        }
        return users;
    }

}
