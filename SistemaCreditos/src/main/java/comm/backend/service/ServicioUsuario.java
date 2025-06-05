package comm.backend.service;


import comm.backend.model.RolUsuario;
import comm.backend.conexion.Conexion;
import comm.backend.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioUsuario {

    // Registrar un nuevo usuario
    public boolean registrarUsuario(String nombre, String usuario, String clave, String correo, RolUsuario rol) {
        if (buscarPorUsuario(usuario) != null) {
            return false; // Usuario ya existe
        }

        String sql = "INSERT INTO usuario(nombre, usuario, clave, correo, rol) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, usuario);
            ps.setString(3, clave);
            ps.setString(4, correo);
            ps.setString(5, rol.name());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    // Buscar un usuario por su nombre de usuario
    public Usuario buscarPorUsuario(String nombreUsuario) {
        String sql = "SELECT * FROM usuario WHERE usuario = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("clave"),
                        rs.getString("correo"),
                        RolUsuario.valueOf(rs.getString("rol"))
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    // Autenticación por usuario y clave
    public Usuario autenticar(String nombreUsuario, String clave) {
        Usuario u = buscarPorUsuario(nombreUsuario);
        if (u != null && u.getClave().equals(clave)) {
            return u;
        }
        return null;
    }

    // Listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("clave"),
                        rs.getString("correo"),
                        RolUsuario.valueOf(rs.getString("rol"))
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    // Eliminar usuario por ID
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    // Actualizar usuario existente
    public boolean actualizarUsuario(Usuario u) {
        String sql = "UPDATE usuario SET nombre = ?, usuario = ?, clave = ?, correo = ?, rol = ? WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getClave());
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getRol().name());
            ps.setInt(6, u.getId());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }
}
