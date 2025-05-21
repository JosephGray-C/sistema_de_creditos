package Tester.Usuario;

import model.Usuario;
import service.ServicioUsuario;

public class TesterBuscarUsuario {

    public static void main(String[] args) {
        ServicioUsuario servicio = new ServicioUsuario();

        Usuario usuario = servicio.buscarPorUsuario("carlos");
        if (usuario != null) {
            System.out.println("Usuario encontrado:");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Usuario: " + usuario.getUsuario());
            System.out.println("Correo: " + usuario.getCorreo());
            System.out.println("Rol: " + usuario.getRol());
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}
