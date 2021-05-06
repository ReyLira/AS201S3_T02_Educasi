package controlador;

import dao.ActividadDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.ActividadModel;

@Named(value = "actividadControlador")
@SessionScoped
public class ActividadControlador implements Serializable {
    ActividadModel actividad = new ActividadModel();
    private List<ActividadModel> lstActividad;
    private ActividadModel selectedActividad;
    @PostConstruct
    public void start() {
        try {
            listarActividad();
        } catch (Exception ex) {
            Logger.getLogger(ActividadControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void limpiar() {

        actividad = new ActividadModel();
    }
    public void listarActividad() throws Exception {
        ActividadDao dao;
        try {
            dao = new ActividadDao();
            lstActividad = dao.listarActividad();
        } catch (Exception e) {
            throw e;
        }

    }
    public void registrarActividad() throws Exception {
        ActividadDao dao;
        try {
            dao = new ActividadDao();
            dao.registrarActividad(actividad);
            listarActividad();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "REGISTRADO CORRECTAMENTE", null));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL REGISTRAR", null));
        }
    }

    public void modificarActividad() throws Exception {
        ActividadDao dao;
        try {
            dao = new ActividadDao();
            dao.modificarActividad(selectedActividad);
            listarActividad();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO CORRECTAMENTE", null));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL MODIFICAR", null));
        }
    }
    
    public void eliminarActividad() throws Exception {
        ActividadDao dao;
        try {
            dao = new ActividadDao();
            dao.eliminarActividad(selectedActividad);
            listarActividad();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO CORRECTAMENTE", null));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL ELIMINAR", null));
        }
    }

    public ActividadModel getActividad() {
        return actividad;
    }

    public void setActividad(ActividadModel actividad) {
        this.actividad = actividad;
    }

    public List<ActividadModel> getLstActividad() {
        return lstActividad;
    }

    public void setLstActividad(List<ActividadModel> lstActividad) {
        this.lstActividad = lstActividad;
    }

    public ActividadModel getSelectedActividad() {
        return selectedActividad;
    }

    public void setSelectedActividad(ActividadModel selectedActividad) {
        this.selectedActividad = selectedActividad;
    }
}
