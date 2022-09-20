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
    private String email;
    private String psid;
    private String idConversacion;
    private Integer telefono;
    private String extension;
    private String id_whatsapp;

    public UserModel() {

    }

    public UserModel(String nombre, String email, String psid, Integer id, String idConversacion) {
        this.nombre = nombre;
        this.email = email;
        this.psid = psid;
        this.id = id;
        this.idConversacion = idConversacion;
    }

    public UserModel(String nombre, Integer telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    

    public UserModel(int id, String nombre, String email, String psid, String idConversacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Nombre = " + nombre + ", PSID = " + psid;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getId_whatsapp() {
        return id_whatsapp;
    }

    public void setId_whatsapp(String id_whatsapp) {
        this.id_whatsapp = id_whatsapp;
    }
    
    
    
}
