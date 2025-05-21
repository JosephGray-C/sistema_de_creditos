package Tester.Pago;

import model.Pago;
import service.ServicioPago;

import java.sql.Date;
import java.sql.SQLException;

public class TesterRegistrarPago {

    public static void main(String[] args) {
        ServicioPago servicio = new ServicioPago();

        Pago p = new Pago();
        p.setIdCredito(1); // Asegúrate de que el crédito con ID 1 exista
        p.setFechaPago(new Date(System.currentTimeMillis()));
        p.setMontoPagado(30000);
        p.setEstado("Registrado");

        try {
            servicio.registrarPago(p);
            System.out.println("Pago registrado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al registrar pago: " + e.getMessage());
        }
    }
}
