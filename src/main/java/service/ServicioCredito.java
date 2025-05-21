package service;

import Conexion.Conexion;
import model.Credito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioCredito {

    // Registrar nuevo crédito
    public void insertar(Credito credito) throws SQLException {
        String sql = "INSERT INTO credito(id_cliente, monto, tasa_interes, plazo_meses, fecha_inicio, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, credito.getIdCliente());
            ps.setDouble(2, credito.getMonto());
            ps.setDouble(3, credito.getTasaInteres());
            ps.setInt(4, credito.getPlazoMeses());
            ps.setDate(5, credito.getFechaInicio());
            ps.setString(6, credito.getEstado());
            ps.executeUpdate();
        }
    }

    // Buscar crédito por ID (útil para verificar actualizaciones y eliminaciones)
    public Credito buscarPorId(int idCredito) throws SQLException {
        String sql = "SELECT * FROM credito WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCredito);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Credito(
                        rs.getInt("id"),
                        rs.getInt("id_cliente"),
                        rs.getDouble("monto"),
                        rs.getDouble("tasa_interes"),
                        rs.getInt("plazo_meses"),
                        rs.getDate("fecha_inicio"),
                        rs.getString("estado")
                );
            }
        }
        return null;
    }

    // Listar créditos de un cliente específico
    public List<Credito> listarPorCliente(int idCliente) throws SQLException {
        List<Credito> lista = new ArrayList<>();
        String sql = "SELECT * FROM credito WHERE id_cliente = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Credito(
                        rs.getInt("id"),
                        rs.getInt("id_cliente"),
                        rs.getDouble("monto"),
                        rs.getDouble("tasa_interes"),
                        rs.getInt("plazo_meses"),
                        rs.getDate("fecha_inicio"),
                        rs.getString("estado")
                ));
            }
        }
        return lista;
    }

    // Cambiar el estado de un crédito
    public void cambiarEstado(int idCredito, String nuevoEstado) throws SQLException {
        String sql = "UPDATE credito SET estado = ? WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idCredito);
            ps.executeUpdate();
        }
    }

    // Eliminar un crédito
    public void eliminarCredito(int idCredito) throws SQLException {
        String sql = "DELETE FROM credito WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCredito);
            ps.executeUpdate();
        }
    }
}
