package Tester.Usuario;

import model.RolUsuario;
import model.Usuario;
import service.ServicioUsuario;

public class TesterEditarUsuario {

    public static void main(String[] args) {
        ServicioUsuario servicio = new ServicioUsuario();

        Usuario usuario = servicio.buscarPorUsuario("ana");
        if (usuario != null) {
            System.out.println("Usuario original: " + usuario.getNombre() + " | Correo: " + usuario.getCorreo());

            usuario.setNombre("Ana P. Modificada");
            usuario.setCorreo("nuevo_correo@mail.com");
            usuario.setClave("claveActualizada");
            usuario.setRol(RolUsuario.Administrador);

            boolean actualizado = servicio.actualizarUsuario(usuario);
            System.out.println("¿Actualización exitosa?: " + actualizado);
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}
