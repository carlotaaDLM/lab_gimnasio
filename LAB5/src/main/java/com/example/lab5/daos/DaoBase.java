package com.example.lab5.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {

    // Cambia "gimnasio" por el nombre de la BD que crea el script del lab
    private static final String URL = "jdbc:mysql://localhost:3306/gimnasio";
    private static final String USER = "root";
    private static final String PASS = "root";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
