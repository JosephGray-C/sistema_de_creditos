package Tester.Credito;

import service.ServicioCredito;

import java.sql.SQLException;

public class TesterEditarCredito {
    public static void main(String[] args) {
        ServicioCredito servicio = new ServicioCredito();

        int idCredito = 1; // Asegúrate de que exista este ID
        String nuevoEstado = "Cancelado";

        try {
            servicio.cambiarEstado(idCredito, nuevoEstado);
            System.out.println("Estado actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al cambiar el estado del crédito: " + e.getMessage());
        }
    }
}
