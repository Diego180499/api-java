package com.diego.api.mapper.user;

import com.diego.api.controllers.user.dto.request.UserDTO;
import com.diego.api.controllers.user.dto.response.UserToShowDTO;
import com.diego.api.repositories.models.UserModel;
import java.util.ArrayList;

public class UserMap {

    public static UserModel toUserModel(UserDTO user) {
        String nombre = user.getNombre();
        Integer extension = user.getExtension();
        Integer telefono = user.getTelefono();
        String nickName = user.getApodo();
        UserModel userModel = new UserModel(0, nombre, telefono, extension, nickName);
        return userModel;
    }

    public static ArrayList<UserToShowDTO> toUsersShow(ArrayList<UserModel> users) {

        ArrayList<UserToShowDTO> usersShow = new ArrayList<>();

        for (UserModel user : users) {
            UserToShowDTO userShowDTO = new UserToShowDTO();
            userShowDTO.setNombre(user.getNombre());
            userShowDTO.setId(user.getId());
            userShowDTO.setNickName(user.getNickName());
            usersShow.add(userShowDTO);
        }

        return usersShow;
    }

}
