package com.example.lab5.daos;

import com.example.lab5.beans.Entrenador;

import java.sql.*;
import java.util.ArrayList;

public class DaoEntrenador extends DaoBase {

    // 1) Listar todos los entrenadores
    public ArrayList<Entrenador> listarEntrenadores() {
        ArrayList<Entrenador> lista = new ArrayList<>();
        String sql = "SELECT * FROM entrenadores ORDER BY id_entrenador";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(llenarEntrenador(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // 2) Obtener un entrenador por ID (null si no existe)
    public Entrenador obtenerEntrenadorPorId(int idEntrenador) {
        Entrenador entrenador = null;
        String sql = "SELECT * FROM entrenadores WHERE id_entrenador = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEntrenador);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    entrenador = llenarEntrenador(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenador;
    }

    // 3) Registrar un nuevo entrenador
    //    id_entrenador es AUTO_INCREMENT: no se envía, MySQL lo genera
    public void registrarEntrenador(Entrenador entrenador) {
        String sql = "INSERT INTO entrenadores (nombre, especialidad, telefono) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entrenador.getNombre());
            ps.setString(2, entrenador.getEspecialidad());
            ps.setString(3, entrenador.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4) Actualizar un entrenador
    public void actualizarEntrenador(Entrenador entrenador) {
        String sql = "UPDATE entrenadores SET nombre = ?, especialidad = ?, telefono = ? "
                + "WHERE id_entrenador = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entrenador.getNombre());
            ps.setString(2, entrenador.getEspecialidad());
            ps.setString(3, entrenador.getTelefono());
            ps.setInt(4, entrenador.getIdEntrenador());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5) Borrar un entrenador
    //    Devuelve false si MySQL no lo deja borrar (por ejemplo, porque tiene
    //    clases asignadas y la FK de "clases" lo impide)
    public boolean borrarEntrenador(int idEntrenador) {
        String sql = "DELETE FROM entrenadores WHERE id_entrenador = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEntrenador);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Entrenador llenarEntrenador(ResultSet rs) throws SQLException {
        Entrenador e = new Entrenador();
        e.setIdEntrenador(rs.getInt("id_entrenador"));
        e.setNombre(rs.getString("nombre"));
        e.setEspecialidad(rs.getString("especialidad"));
        e.setTelefono(rs.getString("telefono"));
        return e;
    }
}
