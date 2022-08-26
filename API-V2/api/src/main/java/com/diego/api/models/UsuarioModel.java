
package com.diego.api.models;


import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;
    
    
    private String nombre;
    private String email;
    private String psid;
    private String idConversacion;

    public UsuarioModel() {
        
    }

    public UsuarioModel(String nombre, String email, String psid, Integer id, String idConversacion) {
        this.nombre = nombre;
        this.email = email;
        this.psid = psid;
        this.id=id;
        this.idConversacion = idConversacion;
    }
    
    

    public UsuarioModel(int id, String nombre, String email, String psid, String idConversacion) {
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

    @Override
    public String toString() {
        return "Nombre = " + nombre + ", PSID = " + psid;
    }
    
    
    

}
