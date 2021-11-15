/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.CuotaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.CuotaModel;
import servicio.Reporte;

/**
 *
 * @author ZERO
 */
@Named(value = "cuotaC")
@SessionScoped
public class CuotaC implements Serializable {

    private CuotaModel cuot;
    private CuotaImpl dao;
    private List<CuotaModel> listadoCuot;
    private List<CuotaModel> listAct;
    private List<CuotaModel> listadoFecha;
    private List<CuotaModel> listDet;
    public CuotaC() {
        cuot = new CuotaModel();
        dao = new CuotaImpl();
    }

    public void registrar() throws Exception {
        try {
            Logger.getGlobal().log(Level.INFO, "Error== ", cuot);
            dao.registrar(cuot);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en registrarC {0}", e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(cuot);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en modificarC {0}", e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(cuot);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en eliminarC {0}", e.getMessage());
        }
    }

    public void limpiar() {
        cuot = new CuotaModel();
    }

    public void listar() {
        try {
            listadoCuot = dao.listarTodos();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en listarC {0}", e.getMessage());
        }
    }

    public void obtenerCuota() throws Exception {

        try {
            if (cuot.getFKActividad() > 0) {
                cuot.setCantidadCuota(dao.obtenerSaldoCuota(cuot.getFKActividad(), cuot.getFKpersona()));
            }
            
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en obtener cuota C {0}", e.getMessage());
        }

    }

    public void reporteCuota() throws Exception {

        try {
            if (cuot.getFechaSan() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar la fecha en el reporte"));
            }
            if (cuot.getFechaSan() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = cuot.getFechaSan();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parameter1", sts);
                report.exportarPDFGlobal(parameters, "cuota.jasper", fechSystem + " cuota.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteCuotaRango() throws Exception {

        try {
            if (cuot.getFechaReportEntrada() == null || cuot.getFechaReportSalida() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar una fecha en el reporte"));
            }
            if (cuot.getFechaReportEntrada() != null && cuot.getFechaReportSalida() != null) {
                if (cuot.getFechaReportEntrada().after(cuot.getFechaReportSalida())) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha de inicio es mayor a la salida en el reporte"));
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YY");
                    String sts1 = dateFormat.format(cuot.getFechaReportEntrada());
                    String sts2 = dateFormat.format(cuot.getFechaReportSalida());
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date fechaActual = new Date(System.currentTimeMillis());
                    String fechSystem = dateFormat2.format(fechaActual);
                    Reporte report = new Reporte();
                    Map<String, Object> parameters = new HashMap();
                    parameters.put("Parametro1", sts1);
                    parameters.put("Parametro2", sts2);
                    report.exportarPDFGlobal(parameters, "cuotaRango.jasper", fechSystem + " cuotaRango.pdf");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    
    
    //Metodos Generados
    public CuotaModel getCuot() {
        return cuot;
    }

    public void setCuot(CuotaModel cuot) {
        this.cuot = cuot;
    }

    public CuotaImpl getDao() {
        return dao;
    }

    public void setDao(CuotaImpl dao) {
        this.dao = dao;
    }

    public List<CuotaModel> getListadoCuot() {
        return listadoCuot;
    }

    public void setListadoCuot(List<CuotaModel> listadoCuot) {
        this.listadoCuot = listadoCuot;
    }

    public List<CuotaModel> getListAct() {
        try {
            listAct = dao.listarAct();
        } catch (SQLException ex) {
            Logger.getLogger(CuotaC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CuotaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAct;
    }

    public void setListAct(List<CuotaModel> listAct) {
        this.listAct = listAct;
    }

    public List<CuotaModel> getListadoFecha() {
        listadoFecha = new ArrayList<CuotaModel>();
        try {
            listadoFecha = dao.listarFecha();
        } catch (SQLException ex) {
            Logger.getLogger(CuotaC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CuotaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoFecha;
    }

    public void setListadoFecha(List<CuotaModel> listadoFecha) {
        this.listadoFecha = listadoFecha;
    }

    public List<CuotaModel> getListDet() {

        return listDet;
    }

    public void setListDet(List<CuotaModel> listDet) {
        this.listDet = listDet;
    }

}
