package com.diego.demo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.diego.demo.conexion.Conector;
import com.diego.demo.models.UsuarioModel;

public class UsuarioDAO {

    public static Integer insertar(UsuarioModel usuario) {

        String queryInsertar = "INSERT INTO usuario(nombre,apellido,facebook) VALUES (\"" + usuario.getNombre()
                + "\"" + " , \"" + usuario.getApellido() + "\" , \"" + usuario.getFacebook() + "\");";

        try {
            PreparedStatement ps = Conector.getConnection().prepareStatement(queryInsertar);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error insertando", e);
        }
    }

    public static Integer eliminar(int id) {
        String queryEliminar = "DELETE FROM usuario WHERE id = " + id + " ;";
        try {
            PreparedStatement ps = Conector.getConnection().prepareStatement(queryEliminar);
            return ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("Error eliminando", e);
        }
    }

    

}
