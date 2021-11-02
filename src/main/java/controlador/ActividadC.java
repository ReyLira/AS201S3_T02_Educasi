/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ActividadImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.ActividadModel;
import servicio.Reporte;
/**
 *
 * @author EDGARD
 */
@Named(value = "actividadC")
@SessionScoped
public class ActividadC implements Serializable {
    private String str;
    private ActividadModel act;
    private ActividadImpl dao;
    private String est;
    private List<ActividadModel> listEst;
    private List<ActividadModel> listAct;

    public ActividadC() {
        act = new ActividadModel();
        dao = new ActividadImpl();
    }

    public void registrar() throws Exception {
        try {
            act.setNombreActividad(convertid(act.getNombreActividad()));
            Logger.getGlobal().log(Level.INFO, "Error== ",act);
            dao.registrarD(act);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.INFO, "Error== {0}",e.getErrorCode());
            if (e.getErrorCode() == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "La ACTIVIDAD ingresada coincide con otra ACTIVIDAD existente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", "error fatal"));
            }
        }
    }

    public void modificar() throws Exception {
        try {
            act.setNombreActividad(convertid(act.getNombreActividad()));
            dao.modificar(act);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en modificar act C {0}",e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(act);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en eliminar act C {0}",e.getMessage());
        }
    }

    public void limpiar() {
        act = new ActividadModel();
    }
    public String convertid(String str) {
        char ch[] = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            // Si se encuentra el primer carácter de una palabra
            if (i == 0 && ch[i] != ' '
                    || ch[i] != ' ' && ch[i - 1] == ' ') {
                // Si está en minúsculas
                if (ch[i] >= 'a' && ch[i] <= 'z') {
                    // Convertir en mayúsculas
                    ch[i] = (char) (ch[i] - 'a' + 'A');
                }
            } // Si aparte del primer carácter
            // Cualquiera está en mayúsculas
            else if (ch[i] >= 'A' && ch[i] <= 'Z') // Convertir en minúsculas
            {
                ch[i] = (char) (ch[i] + 'a' - 'A');
            }
        }
        String st = new String(ch);
        str = st;
        return str;
    }
    public void listar() {
        try {
            listAct = dao.listarTodos();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en listar act C {0}",e.getMessage());
        }
    }

    public void reporteActividad() throws Exception {
        Reporte report = new Reporte();
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "actividadEstado.jasper", fechSystem + " actividadEstado.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteActividadInactivo() throws Exception {
        Reporte report = new Reporte();
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "actividadEstadoInact.jasper", fechSystem+" actividadEstadoInact.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public ActividadModel getAct() {
        return act;
    }

    public void setAct(ActividadModel act) {
        this.act = act;
    }

    public ActividadImpl getDao() {
        return dao;
    }

    public void setDao(ActividadImpl dao) {
        this.dao = dao;
    }

    public List<ActividadModel> getListAct() {
        try {
            if (est != null && !est.isEmpty()) {

                listAct = dao.ListarPorEstado(est);

            } else {
                this.listAct = dao.listarTodos();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActividadC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ActividadC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAct;
    }

    public void setListAct(List<ActividadModel> listAct) {
        this.listAct = listAct;
    }

    public List<ActividadModel> getListEst() {

        return listEst;
    }

    public void setListEst(List<ActividadModel> listEst) {
        this.listEst = listEst;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

}
