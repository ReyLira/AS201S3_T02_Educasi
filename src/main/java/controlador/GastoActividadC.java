package controlador;

import dao.GastoActividadImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.GastoActividadModel;
import servicio.Reporte;

@Named(value = "gastoActividadC")
@SessionScoped
public class GastoActividadC implements Serializable {

    GastoActividadModel gastoActividad = new GastoActividadModel();
    private List<GastoActividadModel> lstGastoAct;
    private GastoActividadModel selectedGastoActividad;

    @PostConstruct
    public void start() {
        try {
            listarGastoAct();
        } catch (Exception ex) {
        }

    }

    public void limpiar() {
        gastoActividad = new GastoActividadModel();
    }

    public void listarGastoAct() throws Exception {
        GastoActividadImpl dao;
        try {
            dao = new GastoActividadImpl();
            lstGastoAct = dao.listarTodos();
        } catch (Exception e) {
            throw e;
        }
    }

    public void registrarGastoAct() {
        GastoActividadImpl dao;
        try {
            dao = new GastoActividadImpl();
            if (gastoActividad.getRadiobutfin().equals("S")) {
                dao.cambiarEstadoGasto("F", getGastoActividad().getIdact());
                dao.registrar(gastoActividad);
            } else {
                dao.cambiarEstadoGasto("P", getGastoActividad().getIdact());
                dao.registrar(gastoActividad);
            }
            listarGastoAct();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "REGISTRADO CORRECTAMENTE", null));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL REGISTRAR", null));
        }
    }

    public void modificarGastoAct() {
        GastoActividadImpl dao;
        try {
            dao = new GastoActividadImpl();
            dao.modificar(selectedGastoActividad);
            listarGastoAct();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO CORRECTAMENTE", null));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL MODIFICAR", null));
        }
    }

    public void eliminarGastoAct() {
        GastoActividadImpl dao;
        try {
            dao = new GastoActividadImpl();
            dao.eliminar(selectedGastoActividad);
            listarGastoAct();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO CORRECTAMENTE", null));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL ELIMINAR", null));
        }
    }
    
    public void reporteGastoActividad() throws Exception {
        Reporte report = new Reporte();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "GastosActividades.jasper", "GastosPorActividades.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public GastoActividadModel getGastoActividad() {
        return gastoActividad;
    }

    public void setGastoActividad(GastoActividadModel gastoActividad) {
        this.gastoActividad = gastoActividad;
    }

    public List<GastoActividadModel> getLstGastoAct() {
        return lstGastoAct;
    }

    public void setLstGastoAct(List<GastoActividadModel> lstGastoAct) {
        this.lstGastoAct = lstGastoAct;
    }

    public GastoActividadModel getSelectedGastoActividad() {
        return selectedGastoActividad;
    }

    public void setSelectedGastoActividad(GastoActividadModel selectedGastoActividad) {
        this.selectedGastoActividad = selectedGastoActividad;
    }

}
