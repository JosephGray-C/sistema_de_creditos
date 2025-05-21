package Tester.Pago;

import model.Pago;
import service.ServicioPago;

import java.sql.SQLException;
import java.util.List;

public class TesterBuscarPago {

    public static void main(String[] args) {
        ServicioPago servicio = new ServicioPago();
        int idCredito = 1; // Asegúrate de que existan pagos para este crédito

        try {
            List<Pago> pagos = servicio.listarPorCredito(idCredito);
            if (pagos.isEmpty()) {
                System.out.println("No hay pagos registrados para el crédito.");
            } else {
                for (Pago p : pagos) {
                    System.out.println("ID: " + p.getId() + " | Monto: " + p.getMontoPagado()
                            + " | Fecha: " + p.getFechaPago() + " | Estado: " + p.getEstado());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar pagos: " + e.getMessage());
        }
    }
}
