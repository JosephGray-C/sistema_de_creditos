package Tester.Cliente;

import model.Cliente;
import service.ServicioCliente;

public class TesterEliminarCliente {

    public static void main(String[] args) {
        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = servicio.buscarPorCedula("234567890"); // Cambiar según cédula a eliminar
        if (cliente != null) {
            boolean eliminado = servicio.eliminarCliente(cliente.getId());
            System.out.println("¿Eliminación exitosa?: " + eliminado);
        } else {
            System.out.println("Cliente no encontrado para eliminar.");
        }
    }
}
