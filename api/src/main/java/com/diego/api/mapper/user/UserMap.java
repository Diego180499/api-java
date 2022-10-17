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
        UserModel userModel = new UserModel(nombre, telefono, extension, nickName);
        return userModel;
    }

    /* Este método mapea todos los usersModel de la base de datos para enviarlos
    al front. 
     */
    public static ArrayList<UserToShowDTO> toUsersShow(ArrayList<UserModel> users) {

        ArrayList<UserToShowDTO> usersShow = new ArrayList<>();

        for (UserModel user : users) {
            UserToShowDTO userShowDTO = new UserToShowDTO();
            userShowDTO.setNombre(user.getNombre());
            userShowDTO.setNickName(user.getNickName());
            userShowDTO.setTelefono(user.getTelefono());

            usersShow.add(userShowDTO);
        }

        return usersShow;
    }

}
