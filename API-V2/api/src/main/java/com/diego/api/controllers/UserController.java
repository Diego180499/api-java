package com.diego.api.controllers;

import com.diego.api.facebook_manager.DataDTO;
import com.diego.api.facebook_manager.MessageDTO;
import com.diego.api.facebook_manager.ResponseDTO;
import com.diego.api.models.UsuarioModel;
import com.diego.api.service.UserService;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * El rol de esta clase es consumir todos los servicios segun el url y la
 * peticion HTTP esta clase se comunica con la clase UserService.
 *
 * @author HP
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /*P E T I C I ON E S         G E T*/
    @GetMapping()
    public ArrayList<UsuarioModel> getUsers() {
        return userService.getUsers();
    }

    /**
     * Ver los usuarios que han escrito a la página
     *
     * @return
     */
    @GetMapping("/seeUsers")
    public ArrayList<UsuarioModel> allUsers() {

        ResponseDTO response2 = userService.obtenerUsuarios();
        ArrayList<UsuarioModel> usuarios = userService.agregarUsuarios(response2);

        return usuarios;
    }

    /**
     * Ver los usuarios que han escrito a la página
     *
     * @return
     */
    @GetMapping("/seeMessages")
    public ResponseEntity<String> allMessages(@RequestParam(value = "idConversacion") String variable) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /**
     * PETICIONES POST
     */
    @PostMapping()
    public UsuarioModel saveUser(@RequestBody UsuarioModel user) {
        return userService.saveUser(user);
    }

    /**
     * Enviar mensajes a un usuario
     *
     * @param mensaje
     * @return
     */
    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageDTO mensaje) {
        
        userService.enviar2(mensaje);
    }
}
