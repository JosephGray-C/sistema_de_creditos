package comm.backend.service;

import comm.backend.conexion.Conexion;
import comm.backend.model.Credito;
import java.io.Serializable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioCredito implements Serializable {

    private static final long serialVersionUID = 1L;

    // Registrar nuevo crédito
    public void insertar(Credito credito) throws SQLException {
        String sql = "INSERT INTO credito(id_cliente, monto, tasa_interes, plazo_meses, fecha_inicio, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, credito.getIdCliente());
            ps.setDouble(2, credito.getMonto());
            ps.setDouble(3, credito.getTasaInteres());
            ps.setInt(4, credito.getPlazoMeses());

            // ✅ Validación de fechaInicio
            if (credito.getFechaInicio() != null) {
                ps.setDate(5, new java.sql.Date(credito.getFechaInicio().getTime()));
            } else {
                ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            }

            ps.setString(6, credito.getEstado());

            ps.executeUpdate();

            // Debug opcional
            System.out.println(">>> Crédito insertado:");
            System.out.println("Cliente ID: " + credito.getIdCliente());
            System.out.println("Monto: " + credito.getMonto());
            System.out.println("Tasa: " + credito.getTasaInteres());
            System.out.println("Plazo: " + credito.getPlazoMeses());
            System.out.println("Fecha: " + credito.getFechaInicio());
            System.out.println("Estado: " + credito.getEstado());
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

    // Verifica si existe algún crédito con un ID específico
    public boolean existeCredito(int idCredito) {
        String sql = "SELECT COUNT(*) FROM credito WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCredito);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

// Verifica si un cliente tiene créditos activos
    public boolean clienteTieneCreditosActivos(int idCliente) {
        String sql = "SELECT COUNT(*) FROM credito WHERE id_cliente = ? AND estado = 'Activo'";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Credito> listarTodos() throws SQLException, ClassNotFoundException {
        List<Credito> lista = new ArrayList<>();
        String sql = "SELECT * FROM credito";
        try (Connection conn = Conexion.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Credito c = new Credito();
                c.setId(rs.getInt("id"));
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setMonto(rs.getDouble("monto"));
                c.setTasaInteres(rs.getDouble("tasa_interes"));
                c.setPlazoMeses(rs.getInt("plazo_meses"));
                c.setFechaInicio(rs.getDate("fecha_inicio"));
                c.setEstado(rs.getString("estado"));
                lista.add(c);
            }
        }
        return lista;
    }

    public void actualizarCredito(Credito credito) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE credito SET id_cliente = ?, monto = ?, tasa_interes = ?, plazo_meses = ?, fecha_inicio = ?, estado = ? WHERE id = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, credito.getIdCliente());
            stmt.setDouble(2, credito.getMonto());
            stmt.setDouble(3, credito.getTasaInteres());
            stmt.setInt(4, credito.getPlazoMeses());

            if (credito.getFechaInicio() != null) {
                stmt.setDate(5, new java.sql.Date(credito.getFechaInicio().getTime()));
            } else {
                stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            }

            stmt.setString(6, credito.getEstado());
            stmt.setInt(7, credito.getId());

            stmt.executeUpdate();
            System.out.println(">>> Credito actualizado con éxito.");
        }
    }
}
