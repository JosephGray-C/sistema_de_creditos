package comm.frontend.controller;

import comm.backend.model.Credito;
import comm.backend.service.ServicioCliente;
import comm.backend.service.ServicioCredito;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "creditoController")
@SessionScoped
public class CreditoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Credito selectedCredito;
    private List<Credito> listaCreditos;

    private ServicioCredito servicioCredito;
    private ServicioCliente servicioCliente;

    @PostConstruct
    public void init() {
        selectedCredito = new Credito();
        servicioCredito = new ServicioCredito();
        servicioCliente = new ServicioCliente();
        cargarCreditos();
        System.out.println("[DEBUG] CreditoController inicializado");
    }

    public void insert() {
        System.out.println(">>> Insertar crédito: Fecha capturada = " + selectedCredito.getFechaInicio());
        try {
            if (!servicioCliente.buscarPorId(selectedCredito.getIdCliente())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El cliente con ID " + selectedCredito.getIdCliente() + " no existe."));
                return;
            }

            if (selectedCredito.getFechaInicio() == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe ingresar una fecha."));
                return;
            }

            servicioCredito.insertar(selectedCredito);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Crédito registrado correctamente."));

            selectedCredito = new Credito();
            cargarCreditos();

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo insertar el crédito."));
        }
    }

    public void delete(Credito credito) {
        try {
            servicioCredito.eliminarCredito(credito.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Crédito eliminado correctamente."));
            cargarCreditos();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar el crédito."));
        }
    }

    public void update(Credito credito) {
        try {
            if (credito.getFechaInicio() == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe ingresar una fecha válida."));
                return;
            }

            if (!servicioCliente.buscarPorId(credito.getIdCliente())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El cliente no existe."));
                return;
            }

            servicioCredito.actualizarCredito(credito);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Crédito actualizado correctamente."));
            cargarCreditos();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo actualizar el crédito."));
        }
    }

    public void prepareUpdate(Credito credito) {
        this.selectedCredito = credito;
        System.out.println(">>> Crédito seleccionado para editar: " + credito.getFechaInicio());
    }

    public void prepararNuevoCredito() {
        this.selectedCredito = new Credito();
    }

    public void cargarCreditos() {
        try {
            this.listaCreditos = servicioCredito.listarTodos(); // ✅ ahora se cargan todos
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Credito> getAll() {
        return listaCreditos;
    }

    public Credito getSelectedCredito() {
        return selectedCredito;
    }

    public void setSelectedCredito(Credito selectedCredito) {
        this.selectedCredito = selectedCredito;
    }
}
