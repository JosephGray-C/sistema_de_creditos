package service;

import Conexion.Conexion;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioCliente {

    // Registrar un nuevo cliente
    public boolean registrarCliente(String nombre, String cedula, String correo, String telefono, String direccion, Date fechaRegistro) {
        if (buscarPorCedula(cedula) != null) {
            return false; // Cedula ya registrada
        }

        String sql = "INSERT INTO cliente(nombre, cedula, correo, telefono, direccion, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, cedula);
            ps.setString(3, correo);
            ps.setString(4, telefono);
            ps.setString(5, direccion);
            ps.setDate(6, fechaRegistro);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al registrar cliente: " + e.getMessage());
            return false;
        }
    }

    // Buscar cliente por cédula
    public Cliente buscarPorCedula(String cedula) {
        String sql = "SELECT * FROM cliente WHERE cedula = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cedula"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    // Listar todos los clientes
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cedula"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_registro")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    // Eliminar cliente por ID
    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    // Actualizar datos de un cliente
    public boolean actualizarCliente(Cliente c) {
        String sql = "UPDATE cliente SET nombre = ?, cedula = ?, correo = ?, telefono = ?, direccion = ?, fecha_registro = ? WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getCedula());
            ps.setString(3, c.getCorreo());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getDireccion());
            ps.setDate(6, c.getFechaRegistro());
            ps.setInt(7, c.getId());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }
}
