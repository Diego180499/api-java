package com.diego.demo.models;


// esta clase representa la tabla 'usuario' que está en la base de datos

public class UsuarioModel {

    private Long id;
    private String nombre;
    private String apellido;
    private String facebook;

    public void UsuarioModel(String nombre, String apellido, String facebook){
        this.nombre = nombre;
        this.apellido = apellido;
        this.facebook = facebook;
    }

    // metodos SET

    public void setId(Long id){
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public void setFacebook(String facebook){
        this.facebook = facebook;
    }

    // métodos GET
    
    public Long getId(){
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getApellido(){
        return this.apellido;
    }

    public String getFacebook(){
        return this.facebook;
    }




}
