package com.diego.api.service;

import com.diego.api.facebook_manager.MessageDTO;
import com.diego.api.facebook_manager.ResponseDTO;
import com.diego.api.facebook_manager.ResponseMessageDTO;
import com.diego.api.facebook_manager.UserDTO;
import com.diego.api.models.UsuarioModel;
import com.diego.api.repositories.UserRepository;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * El rol de esta clase es implementar la lógica de negocio relacionadas con la
 * base de datos como CREATE, READ (CONSULTAR) , UPDATE, DELETE ... es decir el
 * CRUD y esta clase se comunica con la clase UserRepository.
 *
 * @author HP
 */
@Service

public class UserService {

    private final String PAGE_TOKEN = "EAAX5RTfdxnkBAJlXKWlvz3YNFaPKnoTWWOToZAAXcOX9IB9N0ecUvZAl3gbzjrhAJfMX44f16mAZC3jRBQesnStQhYwMCfMC2I8uFui3cJZCpmd8Ff4L7xDoXXSG1aiYIq8unXXrD7ZCX1xbyVBX4e3t6DQwRJZCSyLl0YJxRIHzdZABu4UoX6XK7dwNB4iFrTlRPPAAaR2qZABjiPyB9V6l";
    RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    public ArrayList<UsuarioModel> getUsers() {
        return (ArrayList<UsuarioModel>) userRepository.findAll();
    }

    public ResponseDTO obtenerUsuarios() {

        restTemplate = new RestTemplate();

        String url = "https://graph.facebook.com/v14.0/111465918342937/conversations?fields=participants&access_token=" + PAGE_TOKEN;
        ResponseDTO response = restTemplate.getForObject(url, ResponseDTO.class);

        return response;
    }

    public UsuarioModel saveUser(UsuarioModel user) {
        return userRepository.save(user);
    }

    public ArrayList<UsuarioModel> agregarUsuarios(ResponseDTO response) {
        ArrayList<UsuarioModel> usuarios = new ArrayList<>();

        for (int i = 0; i < response.getData().length; i++) {
            UserDTO usuario = response.getData()[i].getParticipants().getData().get(0);
            String idConversacion = response.getData()[i].getId();
            UsuarioModel usuarioModel = new UsuarioModel(usuario.getName(), usuario.getEmail(), usuario.getId(), 0, idConversacion);
            usuarios.add(usuarioModel);
        }

        return usuarios;
    }

    public void enviarMensaje(MessageDTO msj) {
        String mensaje = msj.getMensaje();
        String psid = msj.getUsuario();
        restTemplate = new RestTemplate();
        String url = "https://graph.facebook.com/v14.0/111465918342937/messages?recipient={id:" + psid + "}&message={text:'" + mensaje + "'}&messaging_type=RESPONSE&access_token=" + PAGE_TOKEN;

        //restTemplate.postForObject(url, "", ResponseMessageDTO.class);
        restTemplate.postForEntity(url, "", ResponseMessageDTO.class);

//postForObject(url, msj, MessageDTO.class)
    }

    public void enviar2(MessageDTO msj) {
        String mensaje = msj.getMensaje();
        String psid = msj.getUsuario();
        String url = "https://graph.facebook.com/v14.0/111465918342937/messages?recipient={id:" + psid + "}&message={text:'" + mensaje + "'}&messaging_type=RESPONSE&access_token=" + PAGE_TOKEN;
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", psid);
        params.put("text", mensaje);
        URI uri = UriComponentsBuilder.fromUriString(url)
                .buildAndExpand(params)
                .toUri();
        uri = UriComponentsBuilder
                .fromUri(uri)
                .queryParam("name", "myName")
                .build()
                .toUri();
       restTemplate.exchange(uri, HttpMethod.POST, HttpEntity.EMPTY, ResponseMessageDTO.class);
    }

    /**
     * public ArrayList<UsuarioModel> buscarUsuario(String name){ return
     * userRepository.buscarUsuarioNombre(name); }
     */
    //String url = "https://graph.facebook.com/v14.0/111465918342937/messages?recipient={id:" + psid + "}&message={text:'" + mensaje + "'}&messaging_type=RESPONSE&access_token=" + PAGE_TOKEN;
}
