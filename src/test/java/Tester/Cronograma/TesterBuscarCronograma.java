package Tester.Cronograma;

import model.Cronograma;
import service.ServicioCronograma;

import java.sql.SQLException;
import java.util.List;

public class TesterBuscarCronograma {

    public static void main(String[] args) {
        ServicioCronograma servicio = new ServicioCronograma();

        int idCredito = 1; // Asegúrate de que existan cuotas para este crédito

        try {
            List<Cronograma> lista = servicio.listarPorCredito(idCredito);
            if (lista.isEmpty()) {
                System.out.println("No hay cuotas registradas para el crédito " + idCredito);
            } else {
                System.out.println("Cuotas del crédito " + idCredito + ":");
                for (Cronograma c : lista) {
                    System.out.println("Cuota #" + c.getNumeroCuota() + " | Fecha: " + c.getFechaVencimiento()
                            + " | Monto: " + c.getMontoCuota() + " | Estado: " + c.getEstado());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cuotas: " + e.getMessage());
        }
    }
}
