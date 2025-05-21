package Tester.Cliente;

import model.Cliente;
import service.ServicioCliente;

import java.sql.Date;

public class TesterEditarCliente {

    public static void main(String[] args) {
        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = servicio.buscarPorCedula("123456789");
        if (cliente != null) {
            System.out.println("Antes de editar: " + cliente.getNombre() + " - " + cliente.getCorreo());

            cliente.setNombre("Carlos M. Actualizado");
            cliente.setCorreo("nuevo@mail.com");
            cliente.setTelefono("11111111");
            cliente.setDireccion("Cartago");
            cliente.setFechaRegistro(new Date(System.currentTimeMillis()));

            boolean actualizado = servicio.actualizarCliente(cliente);
            System.out.println("¿Actualización exitosa?: " + actualizado);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
}
