package Tester.Pago;

import service.ServicioPago;

import java.sql.SQLException;

public class TesterEditarPago {

    public static void main(String[] args) {
        ServicioPago servicio = new ServicioPago();

        int idPago = 1; // Asegúrate de que exista
        try {
            servicio.anularPago(idPago);
            System.out.println("Pago anulado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al anular pago: " + e.getMessage());
        }
    }
}
