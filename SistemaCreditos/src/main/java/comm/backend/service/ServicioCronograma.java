package comm.backend.service;

import comm.backend.conexion.Conexion;
import comm.backend.model.Cronograma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioCronograma {

    // Registrar una nueva cuota
    public void insertar(Cronograma c) throws SQLException {
        String sql = "INSERT INTO cronograma(id_credito, numero_cuota, fecha_vencimiento, monto_cuota, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getIdCredito());
            ps.setInt(2, c.getNumeroCuota());
            ps.setDate(3, c.getFechaVencimiento());
            ps.setDouble(4, c.getMontoCuota());
            ps.setString(5, c.getEstado());
            ps.executeUpdate();
        }
    }

    // Listar cuotas de un crédito
    public List<Cronograma> listarPorCredito(int idCredito) throws SQLException {
        List<Cronograma> lista = new ArrayList<>();
        String sql = "SELECT * FROM cronograma WHERE id_credito = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCredito);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Cronograma(
                        rs.getInt("id"),
                        rs.getInt("id_credito"),
                        rs.getInt("numero_cuota"),
                        rs.getDate("fecha_vencimiento"),
                        rs.getDouble("monto_cuota"),
                        rs.getString("estado")
                ));
            }
        }
        return lista;
    }

    // Actualizar estado de una cuota
    public void actualizarEstado(int idCuota, String estado) throws SQLException {
        String sql = "UPDATE cronograma SET estado = ? WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setInt(2, idCuota);
            ps.executeUpdate();
        }
    }

    // Eliminar una cuota
    public void eliminarCuota(int idCuota) throws SQLException {
        String sql = "DELETE FROM cronograma WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCuota);
            ps.executeUpdate();
        }
    }
}
