package com.diego.api.repositories.models;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    private String nombre;
    private String nickname;
    private String psid;
    private String idConversacion;
    private Integer telefono;
    private Integer extension;
    private String id_whatsapp;

    public UserModel() {

    }

    public UserModel(String nombre, String nick_name, String psid, Integer id, String idConversacion) {
        this.nombre = nombre;
        this.nickname = nick_name;
        this.psid = psid;
        this.id = id;
        this.idConversacion = idConversacion;
    }

    public UserModel(String nombre, Integer telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public UserModel(Integer id, String nombre, Integer telefono, Integer extension, String nickname) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.extension = extension;
        this.nickname = nickname;
    }

    public UserModel(int id, String nombre, String nick_name, String psid, String idConversacion) {
        this.id = id;
        this.nombre = nombre;
        this.nickname = nick_name;
        this.psid = psid;
        this.idConversacion = idConversacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickName() {
        return nickname;
    }

    public void setNickName(String nickName) {
        this.nickname = nickName;
    }

    public String getPsid() {
        return psid;
    }

    public void setPsid(String psid) {
        this.psid = psid;
    }

    public String getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(String idConversacion) {
        this.idConversacion = idConversacion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getExtension() {
        return extension;
    }

    public void setExtension(Integer extension) {
        this.extension = extension;
    }

    public String getId_whatsapp() {
        return id_whatsapp;
    }

    public void setId_whatsapp(String id_whatsapp) {
        this.id_whatsapp = id_whatsapp;
    }

}
