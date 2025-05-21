
package Tester.Cronograma;

import model.Cronograma;
import service.ServicioCronograma;

import java.sql.Date;
import java.sql.SQLException;

public class TesterRegistrarCronograma {
    public static void main(String[] args) {
        ServicioCronograma servicio = new ServicioCronograma();

        Cronograma cuota = new Cronograma();
        cuota.setIdCredito(1); // Asegúrate de que exista este crédito
        cuota.setNumeroCuota(1);
        cuota.setFechaVencimiento(Date.valueOf("2025-06-20"));
        cuota.setMontoCuota(25000);
        cuota.setEstado("Pendiente");

        try {
            servicio.insertar(cuota);
            System.out.println("Cuota registrada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al registrar cuota: " + e.getMessage());
        }
    }
}
