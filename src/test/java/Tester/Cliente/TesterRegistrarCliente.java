package Tester.Cliente;

import service.ServicioCliente;

import java.sql.Date;

public class TesterRegistrarCliente {

    public static void main(String[] args) {
        ServicioCliente servicio = new ServicioCliente();

        boolean registrado1 = servicio.registrarCliente("Carlos Marin", "123456789", "carlos@mail.com", "88888888", "San José", new Date(System.currentTimeMillis()));
        boolean registrado2 = servicio.registrarCliente("Ana Pérez", "234567890", "ana@mail.com", "89999999", "Alajuela", new Date(System.currentTimeMillis()));
        boolean registrado3 = servicio.registrarCliente("Carlos Marin", "123456789", "carlos2@mail.com", "87777777", "Heredia", new Date(System.currentTimeMillis())); // Duplicada

        System.out.println("Registro 1 exitoso: " + registrado1);
        System.out.println("Registro 2 exitoso: " + registrado2);
        System.out.println("Registro duplicado (esperado false): " + registrado3);
    }
}
