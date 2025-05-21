package Tester.Cronograma;

import service.ServicioCronograma;

import java.sql.SQLException;

public class TesterEliminarCronograma {

    public static void main(String[] args) {
        ServicioCronograma servicio = new ServicioCronograma();
        int idCuota = 1; // ID de la cuota a eliminar

        try {
            servicio.eliminarCuota(idCuota);
            System.out.println("Cuota eliminada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar cuota: " + e.getMessage());
        }
    }
}
