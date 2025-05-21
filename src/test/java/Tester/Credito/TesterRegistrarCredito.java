
package Tester.Credito;

import model.Credito;
import service.ServicioCredito;

import java.sql.Date;
import java.sql.SQLException;

public class TesterRegistrarCredito {
    public static void main(String[] args) {
        ServicioCredito servicio = new ServicioCredito();

        Credito nuevoCredito = new Credito();
        nuevoCredito.setIdCliente(1); // Asegúrate de que este cliente exista en tu BD
        nuevoCredito.setMonto(500000);
        nuevoCredito.setTasaInteres(10.5);
        nuevoCredito.setPlazoMeses(24);
        nuevoCredito.setFechaInicio(new Date(System.currentTimeMillis()));
        nuevoCredito.setEstado("Activo");

        try {
            servicio.insertar(nuevoCredito);
            System.out.println("Crédito registrado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al registrar crédito: " + e.getMessage());
        }
    }
}
