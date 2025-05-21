package service;

import java.sql.*;
import java.util.*;
import model.Pago;
import Conexion.Conexion;

public class ServicioPago {

    public void registrarPago(Pago p) throws SQLException {
        String sql = "INSERT INTO pago(id_credito, fecha_pago, monto_pagado, estado) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getIdCredito());
            ps.setDate(2, p.getFechaPago());
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
}
