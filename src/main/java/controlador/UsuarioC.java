/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioImpl;
import static dao.UsuarioImpl.validar;
import static dao.UsuarioImpl.validarCorreo;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.UsuarioModel;
import servicio.MailJava;
import static servicio.MailJava.notificarCorreo;
import servicio.Password;

/**
 *
 * @author EDGARD
 */
@Data
@Named(value = "usuarioC")
@SessionScoped
public class UsuarioC implements Serializable {

    UsuarioImpl dao;
    UsuarioModel usuarrio;
    String user;
    String consult;
    String pass;
    int captcha = 0;
    int intentos = 0;
    boolean bloquear = false;

    public UsuarioC() {
        usuarrio = new UsuarioModel();
        dao = new UsuarioImpl();
    }

    public void ingres() throws Exception {
        try {
            usuarrio = dao.ingresoLogin(usuarrio.getDNI(), usuarrio.getPass());

            System.out.println(usuarrio.getDNI());
            System.out.println(usuarrio.getEmail());
            System.out.println(usuarrio.getRol());

            
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en login_C {0} ", e.getMessage());
            e.printStackTrace();
        }
    }
    public void acceso(){
        try {
            this.ingres();
            if (dao.logueo == false) {
                intentos++;
                switch (intentos) {
                    case 1:
                        setIntentos(1);
                        setCaptcha(0);
                        Logger.getGlobal().log(Level.INFO, "intentos igual {} ", intentos);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "1 INTENTO FALLIDO", "Usuario/Contraseña incorrectas"));
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "LE QUEDAN 2 INTENTOS", ""));
                        break;
                    case 2:
                        setIntentos(2);
                        setCaptcha(1);
                        Logger.getGlobal().log(Level.INFO, "intentos igual {} ", intentos);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "2 INTENTO FALLIDO", "Usuario/Contraseña incorrectas"));
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "LE QUEDA 1 INTENTO", ""));
                        break;
                    case 3:
                        Logger.getGlobal().log(Level.INFO, "intentos igual {} ", intentos);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "3 INTENTO FALLIDO", "Usuario/Contraseña incorrectas"));
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "BLOQUEO DE SEGURIDAD", ""));
                        setIntentos(3);

                        bloquear = true;
                        if (bloquear) {
                            delaySegundo();
                        }
                        if (intentos == 3) {
                            setIntentos(0);
                            setCaptcha(0);

                        }
                        break;
                    default:
                        break;
                }
            } else {
                 if (usuarrio.getRol() != null) switch (usuarrio.getRol()) {
                    case "APODERADO":
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡BIENVENIDO!", "Ingreso Exitoso"));
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/AS201S3_T02_Educasi/faces/vistas/menuContenido2.xhtml");
                        notificarCorreo(usuarrio);
                        break;
                    case "ADMIN    ":
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡BIENVENIDO!", "Ingreso Exitoso"));
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/AS201S3_T02_Educasi/faces/vistas/menuContenido.xhtml");
                        notificarCorreo(usuarrio);
                        break;
                    case "ALUMNO   ":
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡BIENVENIDO!", "Ingreso Exitoso"));
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/AS201S3_T02_Educasi/faces/vistas/menuContenido2.xhtml");
                        notificarCorreo(usuarrio);
                        break;
                    default:
                        System.out.println("no ingresa");
                        break;
                }
            }
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en acceso_C {0} ", e.getMessage());
            e.printStackTrace();
        }
    }
    private static void delaySegundo() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en delaySegundo_C {0} ", e.getMessage());
            e.printStackTrace();
        }
    }

    // Cerrar y limpiar la sesión y direccionar al xhtml inicial del proyecto
    public void cerrarSesion() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/AS201S3_T02_Educasi/");
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(usuarrio);
            usuarrio = dao.validacionUsuario(usuarrio.getDNI(), usuarrio.getEmail());
            if (dao.validar.equals(true)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "La contraseña ha sido modificada con éxito"));
                MailJava.enviarCorreo2(usuarrio);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Ingrese correctamente los datos"));
            }
            limpiar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "error en cambiar contraseña {0}", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR ", "error en cambiar contraseña"));
        }
    }

    public void limpiar() {
        usuarrio = new UsuarioModel();
    }

    public void passAleatorio() throws Exception {
        try {
            Password.passAleatorio2(usuarrio);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "No genero password"));
        }
    }

    public void consultaEmail() throws Exception {
        try {
            servicio.MailJava.consult = getConsult();
            usuarrio = dao.validacionUsuario2(usuarrio.getDNI());
            if (dao.validarCorreo.equals(true)) {
                MailJava.notificarConsultas(usuarrio);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Se ha enviado la consulta respectiva"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "No se pudo enviar la consulta, verifique que su DNI sea de un usuario del sistema"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "No genero password"));
        }
    }
}
