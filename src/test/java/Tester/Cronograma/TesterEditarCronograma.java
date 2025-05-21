package Tester.Cronograma;

import service.ServicioCronograma;

import java.sql.SQLException;

public class TesterEditarCronograma {

    public static void main(String[] args) {
        ServicioCronograma servicio = new ServicioCronograma();

        int idCuota = 1; // Asegúrate de que esta cuota exista
        String nuevoEstado = "Pagado";

        try {
            servicio.actualizarEstado(idCuota, nuevoEstado);
            System.out.println("Estado de la cuota actualizado a: " + nuevoEstado);
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado: " + e.getMessage());
        }
    }
}
