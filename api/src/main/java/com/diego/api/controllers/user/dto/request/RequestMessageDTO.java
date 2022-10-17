package com.diego.api.controllers.user.dto.request;

public class RequestMessageDTO {

    private String mensaje;
    private Integer telefono;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

}
