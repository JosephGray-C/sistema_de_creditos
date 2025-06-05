package comm.backend.service;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import comm.backend.conexion.Conexion;
import comm.backend.model.Pago;

public class ServicioPago implements Serializable {

    private static final long serialVersionUID = 1L;

    public void registrarPago(Pago p) throws SQLException {
        String sql = "INSERT INTO pago(id_credito, fecha_pago, monto_pagado, estado) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getIdCredito());
            ps.setDate(2, new java.sql.Date(p.getFechaPago().getTime()));
            ps.setDouble(3, p.getMontoPagado());
            ps.setString(4, p.getEstado());
            ps.executeUpdate();
        }
    }

    public List<Pago> listarPorCredito(int idCredito) throws SQLException {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pago WHERE id_credito = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCredito);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Pago(
                        rs.getInt("id"),
                        rs.getInt("id_credito"),
                        rs.getDate("fecha_pago"),
                        rs.getDouble("monto_pagado"),
                        rs.getString("estado")
                ));
            }
        }
        return lista;
    }

    public List<Pago> listarTodos() throws SQLException {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pago";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Pago(
                        rs.getInt("id"),
                        rs.getInt("id_credito"),
                        rs.getDate("fecha_pago"),
                        rs.getDouble("monto_pagado"),
                        rs.getString("estado")
                ));
            }
        }
        return lista;
    }

    public boolean creditoExiste(int creditoId) {
        String sql = "SELECT 1 FROM credito WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, creditoId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getTotalPagado(int creditoId) {
        String sql = "SELECT SUM(monto_pagado) FROM pago WHERE id_credito = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, creditoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getMontoCredito(int creditoId) {
        String sql = "SELECT monto FROM credito WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, creditoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("monto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public boolean isCreditoPagado(int creditoId) {
        String sql = "SELECT estado FROM credito WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, creditoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "Pagado".equalsIgnoreCase(rs.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void anularPago(int idPago) throws SQLException {
        String sql = "UPDATE pago SET estado = 'Anulado' WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPago);
            ps.executeUpdate();
        }
    }

    public void eliminarPago(int idPago) throws SQLException {
        String sql = "DELETE FROM pago WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPago);
            ps.executeUpdate();
        }
    }

    public void actualizarPago(Pago pago) throws SQLException {
        String sql = "UPDATE pago SET id_credito=?, fecha_pago=?, monto_pagado=?, estado=? WHERE id=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pago.getIdCredito());
            ps.setDate(2, new java.sql.Date(pago.getFechaPago().getTime()));
            ps.setDouble(3, pago.getMontoPagado());
            ps.setString(4, pago.getEstado());
            ps.setInt(5, pago.getId());
            ps.executeUpdate();
        }
    }
}
