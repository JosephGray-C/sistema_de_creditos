package Tester.Usuario;

import model.Usuario;
import service.ServicioUsuario;

public class TesterEliminarUsuario {

    public static void main(String[] args) {
        ServicioUsuario servicio = new ServicioUsuario();

        Usuario usuario = servicio.buscarPorUsuario("ana"); // Cambiar según el usuario a eliminar
        if (usuario != null) {
            boolean eliminado = servicio.eliminarUsuario(usuario.getId());
            System.out.println("¿Eliminación exitosa?: " + eliminado);
        } else {
            System.out.println("Usuario no encontrado para eliminar.");
        }
    }
}
