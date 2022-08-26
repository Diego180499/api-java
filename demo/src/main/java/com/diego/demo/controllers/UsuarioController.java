package com.diego.demo.controllers;

import java.util.ArrayList;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diego.demo.dao.UsuarioDAO;
import com.diego.demo.models.UsuarioModel;
import com.diego.demo.parser.Parser;

@RestController
public class UsuarioController {

    /* P E T I C I ON E S G E T */
    @GetMapping("/pruebaGet")
    public String obtenerMensaje(@RequestParam(value = "mensaje", defaultValue = "hola mundo") String variable) {
        return "Mensaje: " + variable;
    }

    /* P E T I C I O N E S P O S T */
    @PostMapping("insertar")
    public ResponseEntity<String> insertar(@RequestBody String json) {
        ArrayList<String> datos = Parser.insert(json);
        String nombre = datos.get(0);
        String apellido = datos.get(1);
        String fb = datos.get(2);

        UsuarioModel usuario = new UsuarioModel();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setFacebook(fb);

        UsuarioDAO.insertar(usuario);
        return new ResponseEntity<>("inserción exitosa: ", HttpStatus.OK);
    }
}
