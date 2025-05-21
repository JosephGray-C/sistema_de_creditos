
package service;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.AlertaMora;

public class ServicioArlertaMora {
 public void insertarAlerta(AlertaMora alerta) throws SQLException {
        String sql = "INSERT INTO alerta_mora(id_credito, mensaje, fecha_alerta, leido) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, alerta.getIdCredito());
            ps.setString(2, alerta.getMensaje());
            ps.setTimestamp(3, alerta.getFechaAlerta());
            ps.setBoolean(4, alerta.isLeido());
            ps.executeUpdate();
        }
    }

    public List<AlertaMora> listarNoLeidas() throws SQLException {
        List<AlertaMora> lista = new ArrayList<>();
        String sql = "SELECT * FROM alerta_mora WHERE leido = FALSE";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new AlertaMora(
                    rs.getInt("id"),
                    rs.getInt("id_credito"),
                    rs.getString("mensaje"),
                    rs.getTimestamp("fecha_alerta"),
                    rs.getBoolean("leido")
                ));
            }
        }
        return lista;
    }

    public void marcarComoLeida(int id) throws SQLException {
        String sql = "UPDATE alerta_mora SET leido = TRUE WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }   
}
