package com.diego.api.dto.facebook_manager;

public class MessageDTO {
    
    private String mensaje;
    private Integer usuario; //sería el PSID del usuario a quien deseo enviar mensaje

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    
    
    
}
