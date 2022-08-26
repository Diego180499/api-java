package com.diego.api.facebook_manager;

public class MessageDTO {
    
    private String mensaje;
    private String usuario; //sería el PSID del usuario a quien deseo enviar mensaje

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}
