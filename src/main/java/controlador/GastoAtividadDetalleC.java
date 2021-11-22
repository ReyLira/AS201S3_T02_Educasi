/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.GastoActividadDetalleImpl;
import dao.GastoActividadImpl;
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
import modelo.GastoActividadModel;
import servicio.Reporte;

/**
 *
 * @author EDGARD
 */
@Named(value = "gastoAtividadDetalleC")
@SessionScoped
public class GastoAtividadDetalleC implements Serializable {

    private int glob;
    private GastoActividadModel gasAct;
    private GastoActividadDetalleImpl dao;
    private String actss;
    private List<GastoActividadModel> listGasAct;
    private List<GastoActividadModel> listAct;
    private List<GastoActividadModel> listDet;

    public GastoAtividadDetalleC() {
        gasAct = new GastoActividadModel();
        dao = new GastoActividadDetalleImpl();
    }

    public void reporteGastoActividadDetalle() {
        try {
            if (actss == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una ACTIVIDAD"));
            }
            if (actss != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                Reporte report = new Reporte();
                Map<String, Object> parameters = new HashMap();
                parameters.put("Parameter1", actss);
                report.exportarPDFGlobal(parameters, "gastoActividadesDetalle.jasper", fechSystem + " GastoActividadDetalle.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
        }
    }

    public void listar() {
        try {
            listGasAct = dao.listarTodos();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error en listarGastoActividadC {0}", e.getMessage());
        }
    }

    public void actObtener() {
        try {
            if (actss != null && !actss.isEmpty()) {
                System.out.println("cont" + actss);
                listDet = dao.ListarPorActividad(actss);
                glob = Integer.valueOf(actss);
                gasAct = dao.total(glob);
                System.out.println(gasAct.getCANTIDAD());
                System.out.println(gasAct.getMONTO());
                System.out.println(gasAct.getRESTO());
            } else {
                this.listDet = dao.listarTodos();
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonaC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PersonaC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public GastoActividadModel getGasAct() {
        return gasAct;
    }

    public void setGasAct(GastoActividadModel gasAct) {
        this.gasAct = gasAct;
    }

    public String getActss() {
        return actss;
    }

    public void setActss(String actss) {
        this.actss = actss;
    }

    public List<GastoActividadModel> getListGasAct() {
        return listGasAct;
    }

    public void setListGasAct(List<GastoActividadModel> listGasAct) {
        this.listGasAct = listGasAct;
    }

    public List<GastoActividadModel> getListAct() {
        try {
            listAct = dao.listarAct();
        } catch (SQLException ex) {
            Logger.getLogger(CuotaC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CuotaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAct;
    }

    public void setListAct(List<GastoActividadModel> listAct) {
        this.listAct = listAct;
    }

    public List<GastoActividadModel> getListDet() {
        return listDet;
    }

    public void setListDet(List<GastoActividadModel> listDet) {
        this.listDet = listDet;
    }

    public GastoActividadDetalleImpl getDao() {
        return dao;
    }

    public void setDao(GastoActividadDetalleImpl dao) {
        this.dao = dao;
    }

}
