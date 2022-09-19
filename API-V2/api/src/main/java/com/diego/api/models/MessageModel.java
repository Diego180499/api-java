package com.diego.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class MessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    private String psidUsuario;
    private String mensaje;

    public MessageModel() {
    }

    public MessageModel(String psidUsuario, String mensaje) {
        this.psidUsuario = psidUsuario;
        this.mensaje = mensaje;
    }
    
    

    public MessageModel(Integer id, String psidUsuario, String mensaje) {
        this.id = id;
        this.psidUsuario = psidUsuario;
        this.mensaje = mensaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPsidUsuario() {
        return psidUsuario;
    }

    public void setPsidUsuario(String psidUsuario) {
        this.psidUsuario = psidUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
