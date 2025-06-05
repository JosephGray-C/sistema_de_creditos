package comm.frontend.controller;

import comm.backend.model.Cliente;
import comm.backend.service.ServicioCliente;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;

@SuppressWarnings("deprecation")
@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Cliente selectedCliente = new Cliente();
    private final ServicioCliente servicio = new ServicioCliente();

    @PostConstruct
    public void init() {
        System.out.println("[DEBUG] ClienteController inicializado");
        selectedCliente = new Cliente();
    }

    // Insertar cliente nuevo
    public void insert() {
        System.out.println("[DEBUG] insert() fue llamado");

        if (validation()) {
            System.out.println("[DEBUG] Validación pasada");

            if (servicio.correoExiste(selectedCliente.getCorreo())) {
                System.out.println("[DEBUG] Correo duplicado");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este correo ya está registrado."));
                return;
            }

            Date fechaActual = Date.valueOf(LocalDate.now());
            System.out.println("[DEBUG] Insertando cliente: " + selectedCliente.getNombre() + ", " + selectedCliente.getCorreo());

            boolean exito = servicio.registrarCliente(
                    selectedCliente.getNombre(),
                    selectedCliente.getCedula(),
                    selectedCliente.getCorreo(),
                    selectedCliente.getTelefono(),
                    selectedCliente.getDireccion(),
                    fechaActual
            );

            if (exito) {
                System.out.println("[DEBUG] Cliente insertado correctamente");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cliente registrado correctamente."));
                selectedCliente = new Cliente(); // limpiar
            } else {
                System.out.println("[DEBUG] Error: no se pudo insertar el cliente");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar el cliente."));
            }
        } else {
            System.out.println("[DEBUG] Validación fallida");
        }
    }

// Método auxiliar
    private void showFacesError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg));
    }

    // Actualizar cliente
    public void update(Cliente cliente) {
        boolean exito = servicio.actualizarCliente(cliente);
        if (exito) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Cliente actualizado correctamente."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el cliente."));
        }
    }

    // Eliminar cliente
    public void delete(Cliente cliente) {
        boolean exito = servicio.eliminarCliente(cliente.getId());
        if (exito) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cliente eliminado correctamente."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el cliente."));
        }
    }

    public void prepareUpdate(Cliente cliente) {
        this.selectedCliente = cliente;
    }

    public void cancelEdit() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado", "Edición cancelada."));
    }

    public List<Cliente> getAll() {
        return servicio.listarClientes();
    }

    public void goToCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "clientes.xhtml?faces-redirect=true");
    }

    // Validaciones antes de registrar
    public boolean validation() {
        if (selectedCliente.getNombre() == null || selectedCliente.getNombre().trim().isEmpty()) {
            showError("El nombre es obligatorio.");
            return false;
        }

        if (selectedCliente.getCedula() == null || selectedCliente.getCedula().trim().isEmpty()) {
            showError("La cédula es obligatoria.");
            return false;
        }

        if (selectedCliente.getCorreo() == null || selectedCliente.getCorreo().trim().isEmpty()) {
            showError("El correo es obligatorio.");
            return false;
        }

        if (selectedCliente.getCorreo().length() > 150) {
            showError("El correo excede el límite permitido.");
            return false;
        }

        return true;
    }

    private void showError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg));
    }

    // Getters y Setters
    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }
}
