package Tester.Credito;

import model.Credito;
import service.ServicioCredito;

import java.sql.SQLException;
import java.util.List;

public class TesterBuscarCredito {

    public static void main(String[] args) {
        ServicioCredito servicio = new ServicioCredito();
        int idCliente = 1; // Asegúrate de que este cliente tenga créditos

        try {
            List<Credito> lista = servicio.listarPorCliente(idCliente);
            if (lista.isEmpty()) {
                System.out.println("No hay créditos registrados para el cliente " + idCliente);
            } else {
                System.out.println("Créditos del cliente " + idCliente + ":");
                for (Credito c : lista) {
                    System.out.println("ID: " + c.getId() + ", Monto: " + c.getMonto() + ", Estado: " + c.getEstado());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar créditos: " + e.getMessage());
        }
    }
}
