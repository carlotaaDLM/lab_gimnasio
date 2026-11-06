package com.example.lab5.daos;

import com.example.lab5.beans.Clase;

import java.sql.*;
import java.util.ArrayList;

public class DaoClase extends DaoBase {

    // Listar las clases de un entrenador usando su ID
    public ArrayList<Clase> listarClasesPorEntrenador(int idEntrenador) {
        ArrayList<Clase> lista = new ArrayList<>();
        String sql = "SELECT * FROM clases WHERE id_entrenador = ? ORDER BY id_clase";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEntrenador);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Clase c = new Clase();
                    c.setIdClase(rs.getInt("id_clase"));
                    c.setNombreClase(rs.getString("nombre_clase"));
                    c.setIdEntrenador(rs.getInt("id_entrenador"));
                    c.setCuposDisponibles(rs.getInt("cupos_disponibles"));
                    c.setPrecio(rs.getBigDecimal("precio"));
                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
