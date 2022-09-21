package com.diego.api.mapper.facebook.response;

import com.diego.api.client.messages.facebook.model.response.in.show_users.ResponseDTO;
import com.diego.api.client.messages.facebook.model.response.out.show_user.UserResponseDTO;
import java.util.ArrayList;

public class ToUserResponseDTO {

    //Mapea datos hacia usuarios de Facebook (UserDTO)
    public static ArrayList<UserResponseDTO> toUsersDTO(ResponseDTO response) {
        ArrayList<UserResponseDTO> users = new ArrayList<>();

        for (int i = 0; i < response.getData().length; i++) {
            String nombre = response.getData()[i].getParticipants().getData().get(0).getName();
            String psid = response.getData()[i].getParticipants().getData().get(0).getId();
            String email = response.getData()[i].getParticipants().getData().get(0).getEmail();
            String idConversacion = response.getData()[i].getId();
            UserResponseDTO usuario = new UserResponseDTO();
            usuario.setNombre(nombre);
            usuario.setPsid(psid);
            usuario.setIdConversacion(idConversacion);
            usuario.setCorreo(email);
            users.add(usuario);
        }
        return users;
    }

}
