package com.diego.demo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class Conector {
    public static final String URL = "jdbc:mysql://localhost:3306/servicio?autoReconnet=true&useSSL=false";
    public static final String USUARIO = "root";
    public static final String PASSWORD = "diegousac17";

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return (Connection) DriverManager.getConnection(URL, USUARIO, PASSWORD);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }

    }


}
