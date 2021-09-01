package controlador;

import dao.ActividadImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.ActividadModel;
import servicio.Reporte;

@Named(value = "actividadControlador")
@SessionScoped
public class ActividadC implements Serializable {

    ActividadModel actividad = new ActividadModel();
    private List<ActividadModel> lstActividad;
    private List<ActividadModel> lstComboAct;
    private ActividadModel selectedActividad;

    @PostConstruct
    public void start() {
        try {
            listarActividad();
            listarComboAct();
        } catch (Exception ex) {
        }

    }

    public void limpiar() {

        actividad = new ActividadModel();
    }

    public void listarActividad() throws Exception {
        ActividadImpl dao;
        try {
            dao = new ActividadImpl();
            lstActividad = dao.listarTodos();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listarComboAct() {
        ActividadImpl dao;
        try {
            dao = new ActividadImpl();
            lstComboAct = dao.listarComboAct();
        } catch (Exception e) {
        }
    }

    public void registrarActividad() throws Exception {
        ActividadImpl dao;
        try {
            dao = new ActividadImpl();
            dao.registrar(actividad);
            listarActividad();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "REGISTRADO CORRECTAMENTE", null));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL REGISTRAR", null));
        }
    }

    public void modificarActividad() throws Exception {
        ActividadImpl dao;
        try {
            dao = new ActividadImpl();
            dao.modificar(selectedActividad);
            listarActividad();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO CORRECTAMENTE", null));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL MODIFICAR", null));
        }
    }
    
    public void eliminarActividad() throws Exception {
        ActividadImpl dao;
        try {
            dao = new ActividadImpl();
            dao.eliminar(selectedActividad);
            listarActividad();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO CORRECTAMENTE", null));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL ELIMINAR", null));
        }
    }
    
    public void reporteActividad() throws Exception {
        Reporte report = new Reporte();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Actividades.jasper", "Actividades.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
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

    public List<ActividadModel> getLstComboAct() {
        return lstComboAct;
    }

    public void setLstComboAct(List<ActividadModel> lstComboAct) {
        this.lstComboAct = lstComboAct;
    }
}
