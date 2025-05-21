package Tester.Pago;

import service.ServicioPago;

import java.sql.SQLException;

public class TesterEliminarPago {

    public static void main(String[] args) {
        ServicioPago servicio = new ServicioPago();
        int idPago = 1; // Cambiar por un ID válido

        try {
            servicio.eliminarPago(idPago);
            System.out.println("Pago eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar pago: " + e.getMessage());
        }
    }
}
