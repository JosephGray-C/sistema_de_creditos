package Tester.Cliente;

import model.Cliente;
import service.ServicioCliente;

public class TesterBuscarCliente {

    public static void main(String[] args) {
        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = servicio.buscarPorCedula("234567890");
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Correo: " + cliente.getCorreo());
            System.out.println("Teléfono: " + cliente.getTelefono());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
}
