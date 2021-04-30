/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.PersonaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import modelo.Persona;

/**
 *
 * @author ZERO
 */
@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    private Persona per;
    private PersonaImpl dao;
    private String rol;
    private List<Persona> listadoPer;
    private List<Persona> listadoApoderado;

    public PersonaC() {
        per = new Persona();
        dao = new PersonaImpl();
    }

    public void registrar() throws Exception {
        try {
            System.out.println(per.getROL());
            dao.registrar(per);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(per);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void eliminar(Persona pers) throws Exception {
        try {
            dao.eliminar(pers);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarC " + e.getMessage());
        }
    }

    public void limpiar() {
        per = new Persona();
    }

    public void listar() {
        try {
            listadoPer = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    // metodos generados
    public Persona getPer() {
        return per;
    }

    public void setPer(Persona per) {
        this.per = per;
    }

    public PersonaImpl getDao() {
        return dao;
    }

    public void setDao(PersonaImpl dao) {
        this.dao = dao;
    }

    public List<Persona> getListadoPer() {
        try {
            if (rol != null && !rol.isEmpty()) {

                listadoPer = dao.ListarPorRol(rol);
            } else {
                this.listadoPer = dao.listarTodos();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PersonaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoPer;
    }

    public void setListadoPer(List<Persona> listadoPer) {
        this.listadoPer = listadoPer;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<Persona> getListadoApoderado() {
        listadoApoderado = new ArrayList<Persona>();
        if (per.getROL() != null && per.getROL().equals("C") && listadoApoderado.isEmpty()) {
            try {
                listadoApoderado = dao.ListarApoderados();
            } catch (Exception e) {
                System.out.println("error en listar apoderado controlador " + e);
            }
        }
        return listadoApoderado;
    }

}
