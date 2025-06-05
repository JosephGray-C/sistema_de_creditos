package comm.frontend.controller;

import comm.backend.model.Pago;
import comm.backend.service.ServicioPago;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "pagoController")
@SessionScoped
public class PagoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private ServicioPago servicioPago;
    private Pago selectedPago;
    private List<Pago> listaPagos;

    @PostConstruct
    public void init() {
        servicioPago = new ServicioPago();
        selectedPago = new Pago();
        cargarPagos();
        System.out.println("[DEBUG] PagoController inicializado");
    }

    public void insert() {
        try {
            // Validación de campos
            if (selectedPago.getIdCredito() <= 0 || selectedPago.getFechaPago() == null || selectedPago.getMontoPagado() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe completar todos los campos requeridos."));
                return;
            }

            // Estado por defecto si está vacío
            if (selectedPago.getEstado() == null || selectedPago.getEstado().isBlank()) {
                selectedPago.setEstado("Pagado");
            }

            // Si está usando java.util.Date, conviértelo a java.sql.Date
            if (selectedPago.getFechaPago() instanceof java.util.Date) {
                selectedPago.setFechaPago(new Date(selectedPago.getFechaPago().getTime()));
            }

            servicioPago.registrarPago(selectedPago);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Pago registrado correctamente."));

            selectedPago = new Pago(); // limpiar formulario
            cargarPagos(); // refrescar tabla

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo registrar el pago."));
        }
    }

    public void delete(Pago pago) {
        try {
            servicioPago.eliminarPago(pago.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Pago eliminado correctamente."));
            cargarPagos();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el pago."));
        }
    }

    public void cargarPagos() {
        try {
            listaPagos = servicioPago.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            listaPagos = new ArrayList<>();
        }
    }

    public Pago getSelectedPago() {
        return selectedPago;
    }

    public void setSelectedPago(Pago selectedPago) {
        this.selectedPago = selectedPago;
    }

    public List<Pago> getAll() {
        return listaPagos;
    }
}
