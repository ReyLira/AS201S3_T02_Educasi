/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.gson.JsonSyntaxException;
import dao.PersonaImpl;
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
import modelo.PersonaModel;
import static servicio.ReniecS.buscarDni;
import static servicio.ReniecPrueba.buscarDniReniec;
import servicio.Reporte;
import static servicio.MailJava.enviarCorreo;
import servicio.Password;

/**
 *
 * @author EDGARD VIERI RODRIGUEZ HUAMAN
 */
@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    private PersonaModel per;
    private PersonaImpl dao;
    private String rol;
    private String est;
    private List<PersonaModel> listadoUbigeo;
    private List<PersonaModel> listadoPer;
    private List<PersonaModel> listadoApoderado;
    private boolean enabled = true;

    public PersonaC() {
        per = new PersonaModel();
        dao = new PersonaImpl();
    }

    public void buscarPorDNI() throws Exception {
        try {
            buscarDniReniec(per);
            Logger.getGlobal().log(Level.WARNING, per.getApellido());
            enabled = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "DNI encontrado"));
        }catch (NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "el DNI no existe o el servidor a fallado"));
            enabled = false;
        }
        catch (JsonSyntaxException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DEBE INGRESAR UN DNI EXISTENTE", "el formato ingresado no es el correcto o el servidor a fallado"));
            enabled = false;
        } 
    }

    public void passAleatorio() throws Exception {
        try {
            Password.passAleatorio(per);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "No genero password"));
        }
    }

    public void enviarC() {
        try {
            enviarCorreo(per);
            Logger.getGlobal().log(Level.WARNING, per.getApellido());
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "error en buscar enviar correo {0}",e.getMessage());
            e.printStackTrace();
        }
    }

    public void registrar() throws Exception {
        try {
            per.setNombre(convertid(per.getNombre()));
            per.setApellido(convertid(per.getApellido()));
            Logger.getGlobal().log(Level.WARNING, per.getROL());
            dao.registrarD(per);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            enviarCorreo(per);
            limpiar();
            listar();
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.WARNING, "error== {0}",e.getErrorCode());
            if (e.getErrorCode() == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "el DNI ingresado coincide con otro usuario existente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", "error fatal"));
            }
        }
    }

    public void modificar() throws Exception {
        try {
            per.setNombre(convertid(per.getNombre()));
            per.setApellido(convertid(per.getApellido()));
            dao.modificar(per);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "error en modificar c {0}",e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(per);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "error en eliminar c {0}",e.getMessage());
        }
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

    public void limpiar() {
        per = new PersonaModel();
    }

    public void listar() {
        try {
            listadoPer = dao.listarTodos();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "error en listar c {0}",e.getMessage());
        }
    }

    public void reportePersonaAdmin() throws Exception {
        Reporte report = new Reporte();
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "personaAdmin.jasper", fechSystem + " personaAdmin.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reportePersonaApod() throws Exception {
        Reporte report = new Reporte();
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "personaApoderado.jasper", fechSystem + " personaApoderado.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reportePersonaAlum() throws Exception {
        Reporte report = new Reporte();
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "personaAlumno.jasper", fechSystem + " personaAlumno.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    // metodos generados
    public PersonaModel getPer() {
        return per;
    }

    public void setPer(PersonaModel per) {
        this.per = per;
    }

    public PersonaImpl getDao() {
        return dao;
    }

    public void setDao(PersonaImpl dao) {
        this.dao = dao;
    }

    public List<PersonaModel> getListadoPer() {
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

    public void setListadoPer(List<PersonaModel> listadoPer) {
        this.listadoPer = listadoPer;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<PersonaModel> getListadoApoderado() {
        listadoApoderado = new ArrayList<PersonaModel>();
        if (per.getROL() != null && per.getROL().equals("ALUMNO") && listadoApoderado.isEmpty()) {
            try {
                listadoApoderado = dao.ListarApoderados();
            } catch (Exception e) {
                Logger.getGlobal().log(Level.WARNING, "error en listar apoderado controlador {0}",e.getMessage());
            }
        }
        return listadoApoderado;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    public List<PersonaModel> getListadoUbigeo() {
        listadoUbigeo = new ArrayList<PersonaModel>();
        try {
            listadoUbigeo = dao.listarUbigeo();
        } catch (SQLException ex) {
            Logger.getLogger(CuotaC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CuotaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoUbigeo;
    }

    public void setListadoUbigeo(List<PersonaModel> listadoUbigeo) {
        this.listadoUbigeo = listadoUbigeo;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
