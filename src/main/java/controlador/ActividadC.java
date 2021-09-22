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
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.ActividadModel;

/**
 *
 * @author EDGARD
 */
@Named(value = "actividadC")
@SessionScoped
public class ActividadC implements Serializable {

   private ActividadModel act;
    private ActividadImpl dao;
    
    private List<ActividadModel> listAct;
    public ActividadC() {
        act = new ActividadModel();
        dao = new ActividadImpl();
    }
    public void registrar() throws Exception {
        try {
            System.out.println(act);
            dao.registrarD(act);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            if (e.getErrorCode() == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "La ACTIVIDAD ingresada coincide con otra ACTIVIDAD existente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", "error fatal"));
            }
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(act);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(act);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarC " + e.getMessage());
        }
    }
    
    public void limpiar() {
        act = new ActividadModel();
    }

    public void listar() {
        try {
            listAct = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
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
        return listAct;
    }

    public void setListAct(List<ActividadModel> listAct) {
        this.listAct = listAct;
    }
    
}
