/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.CuotaDetalleImpl;
import dao.CuotaImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.CuotaModel;
import servicio.Reporte;

/**
 *
 * @author EDGARD
 */
@Named(value = "cuotaDetalleC")
@SessionScoped
public class CuotaDetalleC implements Serializable {

    private int glob;
    private String perss;
    private CuotaModel cuot;
    private CuotaDetalleImpl dao;
    private List<CuotaModel> listadoCuot;
    private List<CuotaModel> listDet;
    private List<CuotaModel> litTotal;

    public CuotaDetalleC() {
        cuot = new CuotaModel();
        dao = new CuotaDetalleImpl();
    }

    public void reporteCuotaDetalle() {
        try {
            if (perss == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una persona"));
            }
            if (perss != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                Reporte report = new Reporte();
                Map<String, Object> parameters = new HashMap();
                parameters.put("Parameter1", perss);
                report.exportarPDFGlobal(parameters, "cuotaDetallePers.jasper", fechSystem + " cuotaDetalle.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
        }
    }

    public void listar() {
        try {
            listadoCuot = dao.listarTodos();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, "Error en listarC {0}", e.getMessage());
        }
    }

    public void cuotObtener() throws Exception {
        try {
            if (perss != null && !perss.isEmpty()) {
                System.out.println("cont" + perss);
                listDet = dao.ListarPorPersona(perss);

                glob = Integer.valueOf(perss);
                cuot = dao.total(glob);
                System.out.println(cuot.getCANTIDAD());
                System.out.println(cuot.getMONTO());
                System.out.println(cuot.getRESTO());
            } else {
                this.listDet = dao.listarTodos();
            }

        } catch (SQLException ex) {
            Logger.getLogger(CuotaC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CuotaC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getPerss() {
        return perss;
    }

    public void setPerss(String perss) {
        this.perss = perss;
    }

    public CuotaModel getCuot() {
        return cuot;
    }

    public void setCuot(CuotaModel cuot) {
        this.cuot = cuot;
    }

    public List<CuotaModel> getListadoCuot() {
        return listadoCuot;
    }

    public void setListadoCuot(List<CuotaModel> listadoCuot) {
        this.listadoCuot = listadoCuot;
    }

    public List<CuotaModel> getListDet() {
        return listDet;
    }

    public void setListDet(List<CuotaModel> listDet) {
        this.listDet = listDet;
    }

    public CuotaDetalleImpl getDao() {
        return dao;
    }

    public void setDao(CuotaDetalleImpl dao) {
        this.dao = dao;
    }

    public List<CuotaModel> getLitTotal() {
        return litTotal;
    }

    public void setLitTotal(List<CuotaModel> litTotal) {
        this.litTotal = litTotal;
    }

    public int getGlob() {
        return glob;
    }

    public void setGlob(int glob) {
        this.glob = glob;
    }

}
