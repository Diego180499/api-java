package com.diego.api.mapper.user;

import com.diego.api.dto.user.request.save_user.UserDTO;
import com.diego.api.dto.user.response.show_users.UserToShowDTO;
import com.diego.api.repositories.models.UserModel;
import java.util.ArrayList;

public class UserMap {

    public static UserModel toUserModel(UserDTO user) {
        String nombre = user.getNombre();
        String extension = user.getExtension();
        Integer telefono = user.getTelefono();
        String email = user.getNickName();
        UserModel userModel = new UserModel(0, nombre, telefono, extension, email);
        return userModel;
    }

    public static ArrayList<UserToShowDTO> toUsersShow(ArrayList<UserModel> users) {

        ArrayList<UserToShowDTO> usersShow = new ArrayList<>();

        for (UserModel user : users) {
            UserToShowDTO userShowDTO = new UserToShowDTO();
            userShowDTO.setNombre(user.getNombre());
            userShowDTO.setId(user.getId());
            usersShow.add(userShowDTO);
        }

        return usersShow;
    }

}
