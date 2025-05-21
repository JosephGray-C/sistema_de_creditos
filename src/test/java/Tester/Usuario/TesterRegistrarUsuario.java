package Tester.Usuario;

import model.RolUsuario;
import service.ServicioUsuario;

public class TesterRegistrarUsuario {

    public static void main(String[] args) {
        ServicioUsuario servicio = new ServicioUsuario();

        boolean registrado1 = servicio.registrarUsuario("Carlos Marin", "carlos", "1234", "carlos@mail.com", RolUsuario.Cliente);
        boolean registrado2 = servicio.registrarUsuario("Ana Pérez", "ana", "abcd", "ana@mail.com", RolUsuario.Asesor);
        boolean registrado3 = servicio.registrarUsuario("Carlos Marin", "carlos", "otraClave", "nuevo@mail.com", RolUsuario.Cliente); // Usuario duplicado

        System.out.println("Registro 1 exitoso: " + registrado1);
        System.out.println("Registro 2 exitoso: " + registrado2);
        System.out.println("Registro duplicado (esperado false): " + registrado3);
    }
}
