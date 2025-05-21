package Tester.Credito;

import java.sql.SQLException;
import service.ServicioCredito;

public class TesterEliminarCredito {

    public static void main(String[] args) {
        ServicioCredito servicio = new ServicioCredito();
        int idCredito = 1; // Cambia al ID del crédito que deseas eliminar

        try {
            servicio.eliminarCredito(idCredito);
            System.out.println("Crédito eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el crédito: " + e.getMessage());
        }
    }
}
